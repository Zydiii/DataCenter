package zyd.datacenter.Service.Impl.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Game.GameHistory;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Entities.Room.Room;
import zyd.datacenter.Entities.Room.RoomType;
import zyd.datacenter.Entities.User.Spectator;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Entities.User.UserInRoom;
import zyd.datacenter.Entities.User.UserScore;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Game.GameHistoryRepository;
import zyd.datacenter.Repository.Game.GameOverviewRepository;
import zyd.datacenter.Repository.Room.RoomRepository;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Repository.User.UserScoreRepository;
import zyd.datacenter.Service.Room.RoomService;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.time.FastDateFormat;
import zyd.datacenter.Service.SequenceGenerator.SequenceGeneratorService;

import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {
    //private static final FastDateFormat pattern = FastDateFormat.getInstance("yyyyMMddHHmmss");
    private static final AtomicInteger atomicInteger = new AtomicInteger(1);
    private static ThreadLocal<StringBuilder> threadLocal = new ThreadLocal<StringBuilder>();

    private RoomRepository roomRepository;

    private UserRepository userRepository;

    private GameOverviewRepository gameOverviewRepository;

    private GameHistoryRepository gameHistoryRepository;

    private UserScoreRepository userScoreRepository;

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository, GameOverviewRepository gameOverviewRepository, GameHistoryRepository gameHistoryRepository, UserScoreRepository userScoreRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.gameOverviewRepository = gameOverviewRepository;
        this.gameHistoryRepository = gameHistoryRepository;
        this.userScoreRepository = userScoreRepository;
    }

    public List<Room> getAllRoom(){
        return roomRepository.findAll();
    }

    public Result createRoom(Room room){
        room.setIp(allocatRoom());
        User user = userRepository.findById(room.getOwnerId()).get();
        room.setOwnerUsername(user.getUsername());
        //String gameId = getD(null);
        //String gameId = UUID.randomUUID().toString();

        // 战斗编号
        long gameId = sequenceGenerator.generateSequence(GameOverview.SEQUENCE_NAME);
        room.setGameId(Long.toString(gameId));
        // 房间编号
        long roomId = sequenceGenerator.generateSequence(Room.SEQUENCE_NAME);
        room.setId(Long.toString(roomId));

        roomRepository.insert(room);
        return new Result("创建成功", 1);
    }

    public Result deleteRoom(Room questRoom){
        if(checkOwner(questRoom)){
            roomRepository.deleteById(questRoom.getId());
            return new Result("操作成功", 1);
        }
        else{
            return new Result("没有权限", 0);
        }
    }

    public Result updateRoom(Room questRoom){
        if(checkOwner(questRoom)){
            roomRepository.save(questRoom);
            return new Result("操作成功", 1);
        }
        else{
            return new Result("没有权限", 0);
        }
    }

    public Result joinRoom(UserInRoom userInRoom){
        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        User user = userRepository.findById(userInRoom.getUserId()).get();
        if(room == null)
            return new Result("房间无效", 0);
        else{
            if(room.getState() == 1)
            {
                return new Result("不能加入已经开始的房间", 0);
            }
            if(room.getPlayerNum() < room.getMaxPlayerNum()){
                userInRoom.setUsername(user.getUsername());
                userInRoom.setGameId(room.getGameId());
                userInRoom.setAvatar(user.getAvatarBase());
                room.addUser(userInRoom);
                roomRepository.save(room);
                user.setState(2);
                userRepository.save(user);
                return new Result(room.getIp(), 1);
            }
            else
            {
                return new Result("房间已满", 0);
            }
        }
    }

    public Result leaveRoom(UserInRoom userInRoom){
        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        User user = userRepository.findById(userInRoom.getUserId()).get();
        if(room == null)
            return new Result("房间无效", 0);
        else {
            room.deleteUser(userInRoom.getUserId());
            roomRepository.save(room);
            user.setState(0);
            userRepository.save(user);
            return new Result("操作成功", 1);
        }
    }

    // 匹配，方案待定
    public Result match(UserInRoom userInRoom){
        return new Result("操作成功", 1);
    }

    public Result joinWatchRoom(Spectator spectator){
        Room room = roomRepository.findById(spectator.getRoomId()).get();
        User user = userRepository.findById(spectator.getUserId()).get();
        if(room == null)
            return new Result("房间无效", 0);
        else{
            if(room.getSpectatorsNum() < room.getMaxSpectatorsNum()){
                room.addSpectator(spectator);
                roomRepository.save(room);
                user.setState(4);
                userRepository.save(user);
                return new Result(room.getIp(), 1);
            }
            else
            {
                return new Result("房间已满", 0);
            }
        }
    }

    // 房间分配，负载均衡，方案待定
    private String allocatRoom(){
        String roomIp = "000";
        return roomIp;
    }

    // 检验是否是房主
    private boolean checkOwner(Room questRoom){
        Room room = roomRepository.findById(questRoom.getId()).get();
        return room.getOwnerId().equals(questRoom.getOwnerId());
    }

    public List<Room> getRoom(RoomType roomType){
        return roomRepository.findAllByRoomType(roomType);
    }

    public Result readyInRoom(UserInRoom userInRoom){
        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        room.changeUserState(userInRoom.getUserId(), 1);
        roomRepository.save(room);
        return new Result("准备成功", 1);
    }

    public Result cancelReadyInRoom(UserInRoom userInRoom){
        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        room.changeUserState(userInRoom.getUserId(), 0);
        roomRepository.save(room);
        return new Result("取消准备成功", 1);
    }

    public Room getOneRoom(String roomId){
        return roomRepository.findById(roomId).get();
    }

    public Result beginGame(UserInRoom userInRoom){
        Date date = new Date();

        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        if(room.getOwnerId().equals(userInRoom.getUserId())){
            Set<UserInRoom> users = room.getUsers();
            for(UserInRoom user: users){
                if(user.getState() == 0){
                    return new Result("有玩家未准备，请稍后重试", 0);
                }
            }
            for(UserInRoom user: users){
                user.setState(2);
                user.setBeginTime(date);
                user.setGameId(room.getGameId());
                user.setRoomType(room.getRoomType());
            }
            //room.setUsers(users);
            room.setState(1);

            GameOverview gameOverview = new GameOverview(room.getIp(), date, room.getRoomType(), room.getUsers());
            // 战斗号
            gameOverview.setGameId(room.getGameId());

            roomRepository.save(room);
            gameOverviewRepository.insert(gameOverview);

            return new Result("操作成功，即将开始游戏......", 1);
        }
        else{
            return new Result("没有权限", 0);
        }
    }

    // 对局中需要实时修改房间内用户状态
    public Result changeScore(UserInRoom userInRoom){
        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        room.changeUser(userInRoom);
        roomRepository.save(room);
        return new Result("Ok", 1);
    }

    public Result endGame(Room room){
        Room room1 = roomRepository.findById(room.getId()).get();
        // 写入对局总览
        Date date = new Date();
        GameOverview gameOverview = gameOverviewRepository.getByGameId(room1.getGameId());
        gameOverview.setEndTime(date);

        // 存储每个用户的历史记录
        for(UserInRoom userInRoom: room1.getUsers()){
            userInRoom.setEndTime(date);
            // 添加历史记录
            GameHistory gameHistory = new GameHistory(userInRoom.getUserId(), room1.getGameId());
            gameHistoryRepository.insert(gameHistory);
            // 修改用户状态
            User user = userRepository.findById(userInRoom.getUserId()).get();
            user.setState(1);
            user.setScore(user.getScore() + userInRoom.getScore());
            userRepository.save(user);
            // 添加分数获取记录
            UserScore userScore = new UserScore(userInRoom.getUsername(), userInRoom.getScore());
            userScore.setTimestamp(date.getTime());
            userScore.setGetScoreDate(date);
            userScoreRepository.insert(userScore);
        }

        gameOverview.setUserInRoom(room1.getUsers());
        gameOverviewRepository.save(gameOverview);
        // 恢复房间状态
        room1.setGameId("");
        room1.setPlayerNum(0);
        room1.setSpectatorsNum(0);
        room1.setState(0);
        room1.getUsers().clear();
        room1.getSpectators().clear();

        long gameId = sequenceGenerator.generateSequence(GameOverview.SEQUENCE_NAME);
        room1.setGameId(Long.toString(gameId));
        //room1.setGameId(UUID.randomUUID().toString());
        roomRepository.save(room1);

        return new Result("ok", 1);
    }

    /**
     * 短码生成策略
     * 1307891882965
     * @param lock
     * @return
     */
    public static String getD(String lock) {
        if (Objects.isNull(threadLocal.get())) {
            lock = Objects.isNull(lock) ? UUID.randomUUID().toString() : lock;
            StringBuilder builder = new StringBuilder(ThreadLocalRandom.current().nextInt(0, 999)); // 随机数
            builder.append(Math.abs(lock.hashCode()));// HASH-CODE
            builder.append(atomicInteger.getAndIncrement());// 自增顺序
            threadLocal.set(builder);
        }
        return threadLocal.get().toString();
    }
}