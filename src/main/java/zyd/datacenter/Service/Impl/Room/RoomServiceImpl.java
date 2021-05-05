package zyd.datacenter.Service.Impl.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zyd.datacenter.Entities.Game.GameHistory;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Entities.Room.Camp;
import zyd.datacenter.Entities.Room.Room;
import zyd.datacenter.Entities.Room.RoomType;
import zyd.datacenter.Entities.User.*;
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

import javax.servlet.http.HttpServletRequest;
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

    public String getIp(HttpServletRequest request)
    {
        String ip = IpUtil.getIpAddr(request);
        return ip;
    }

    public List<Room> getAllRoom(){
        return roomRepository.findAll();
    }

    public Result createRoom(Room room){

        // 目前只允许一个玩家用户创建一个房间
        User user = userRepository.findById(room.getOwnerId()).get();

        Set<String> roles = user.getRoles();
        boolean isAdmin = false;
        for(String role : roles)
        {
            if(role.equals("ROLE_ADMIN"))
            {
                isAdmin = true;
                break;
            }
        }

        if(!isAdmin && roomRepository.findAllByOwnerIdAndRoomType(room.getOwnerId(), room.getRoomType()).size() > 10)
            return new Result("你已经创建过一个该类型房间了哦，请不要重复创建", 0);

        // 管理员可以创建多个房间
        room.setIp(allocatRoom());
        room.setOwnerUsername(user.getUsername());
        //String gameId = getD(null);
        //String gameId = UUID.randomUUID().toString();

        // 战斗编号
        long gameId = sequenceGenerator.generateSequence(GameOverview.SEQUENCE_NAME);
        room.setGameId(Long.toString(gameId));
        // 房间编号
        long roomId = sequenceGenerator.generateSequence(Room.SEQUENCE_NAME);
        room.setId(Long.toString(roomId));

        room.setDigitalRoomType(room.getRoomType());

        // 初始化阵营参数
        int campNum = room.getCampNum();
        int eachCampNum = room.getEachCampPlayNum();
        // 房间人数
        room.setMaxPlayerNum(eachCampNum * campNum);
        // 初始化阵营
        List<Camp> camps = new LinkedList<>();
        for(int i = 0; i < campNum; i++)
        {
            Camp camp = new Camp(i + 1, eachCampNum);
            camps.add(camp);
        }
        room.setCamps(camps);

        roomRepository.insert(room);
        return new Result("创建成功", 1);
    }

    public Result joinCamp(UserInRoom userInRoom){
        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        UserInRoom userInRoom1 = null;
        for(UserInRoom user1 : room.getUsers())
        {
            if(user1.getUserId().equals(userInRoom.getUserId()))
            {
                userInRoom1 = user1;
            }
        }
        if(userInRoom1 == null)
            return new Result("fail", 0);
        int toCampId = userInRoom.getCampId();
        int fromCampId = userInRoom1.getCampId();
        if(toCampId == fromCampId)
            return new Result("操作无效", 0);
        Camp toCamp = room.getCamps().get(toCampId - 1); // camp id 需要 -1
        Camp fromCamp = room.getCamps().get(fromCampId - 1); // camp id 需要 -1
        if(toCamp.getUserNum() < toCamp.getMaxUserNum())
        {
            toCamp.joinCamp(userInRoom1);
            fromCamp.leaveCamp(userInRoom1);
            userInRoom1.setCampId(toCampId);
            userInRoom1.setCampName(toCamp.getCampName());
        }
        roomRepository.save(room);
        return new Result("操作成功", 1);
    }

    public Result deleteRoom(Room questRoom){
        if(checkOwner(questRoom)){
            Room room = roomRepository.findById(questRoom.getId()).get();

            // 房间内还有人不能直接删除
            if(room.getPlayerNum() > 0)
                return new Result("房间内还有玩家，请玩家数为 0 后重试", 0);

            roomRepository.deleteById(questRoom.getId());
            return new Result("操作成功", 1);
        }
        else{
            return new Result("没有权限", 0);
        }
    }

    public Result updateRoom(Room questRoom){
        if(checkOwner(questRoom)){
            Room room = roomRepository.findById(questRoom.getId()).get();

            // 房间内还有人不能直接修改
            if(room.getPlayerNum() > 0)
                return new Result("房间内还有玩家，请玩家数为 0 后重试", 0);

            roomRepository.save(questRoom);
            return new Result("操作成功", 1);
        }
        else{
            return new Result("没有权限", 0);
        }
    }

    public Result joinRoom(UserInRoom userInRoom, HttpServletRequest request){
        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        User user = userRepository.findById(userInRoom.getUserId()).get();
        String userIp = getIp(request);
        user.setUserIp(userIp);
        if(room == null)
            return new Result("房间无效", 0);
        else{
            boolean inRoom = false;
            Set<UserInRoom> usersInRoom = room.getUsers();
            for(UserInRoom userInRoom1 : usersInRoom)
            {
                if(userInRoom1.getUserId().equals(userInRoom.getUserId()))
                {
                    inRoom = true;
                    break;
                }
            }
            // 如果之前不在这个房间
            if(!inRoom)
            {
                if(room.getState() == 1)
                {
                    return new Result("不能加入已经开始的房间", 0);
                }
                else if(room.getPlayerNum() >= room.getMaxPlayerNum())
                {
                    return new Result("房间已满", 0);
                }
                else
                {
                    userInRoom.setUsername(user.getUsername());
                    userInRoom.setGameId(room.getGameId());
                    userInRoom.setAvatar(user.getAvatarBase());
                    if(userInRoom.getWeaponName() == null)
                        userInRoom.setWeaponName("0");

                    boolean foundUser = false;
                    for(UserInRoom userInRoom1 : room.getUsers())
                    {
                        if(userInRoom1.getUserId().equals(userInRoom.getUserId()))
                            foundUser = true;
                    }

                    // 为新加入的用户设置阵营
                    if(!foundUser)
                    {
                        for(int i = 0; i < room.getCampNum(); i++)
                        {
                            if(room.getCamps().get(i).getUserNum() < room.getCamps().get(i).getMaxUserNum())
                            {
                                room.getCamps().get(i).joinCamp(userInRoom);
                                userInRoom.setCampId(room.getCamps().get(i).getCampId());
                                userInRoom.setCampName(room.getCamps().get(i).getCampName());
                                userInRoom.setWeaponId(userInRoom.getWeaponName());
                                break;
                            }
                        }
                        room.addUser(userInRoom);
                        roomRepository.save(room);

                    }

                    user.setState(2);
                    userRepository.save(user);
                    return new Result(room.getIp(), 1);
                }
            }
            // 如果之前在这个房间
            else
            {
                user.setState(2);
                userRepository.save(user);
                return new Result(room.getIp(), 1);
            }

//            if(room.getState() == 1)
//            {
//                return new Result("不能加入已经开始的房间", 0);
//            }
//            if(room.getPlayerNum() < room.getMaxPlayerNum()){
//                userInRoom.setUsername(user.getUsername());
//                userInRoom.setGameId(room.getGameId());
//                userInRoom.setAvatar(user.getAvatarBase());
//                if(userInRoom.getWeaponName() == null)
//                    userInRoom.setWeaponName("0");
//
//                boolean foundUser = false;
//                for(UserInRoom userInRoom1 : room.getUsers())
//                {
//                    if(userInRoom1.getUserId().equals(userInRoom.getUserId()))
//                        foundUser = true;
//                }
//
//                // 为新加入的用户设置阵营
//                if(!foundUser)
//                {
//                    for(int i = 0; i < room.getCampNum(); i++)
//                    {
//                        if(room.getCamps().get(i).getUserNum() < room.getCamps().get(i).getMaxUserNum())
//                        {
//                            room.getCamps().get(i).joinCamp(userInRoom);
//                            userInRoom.setCampId(room.getCamps().get(i).getCampId());
//                            userInRoom.setCampName(room.getCamps().get(i).getCampName());
//                            userInRoom.setWeaponId(userInRoom.getWeaponName());
//                            break;
//                        }
//                    }
//                    room.addUser(userInRoom);
//                    roomRepository.save(room);
//
//                }
//
//                user.setState(2);
//                userRepository.save(user);
//                return new Result(room.getIp(), 1);
//            }
//            else
//            {
//                return new Result("房间已满", 0);
//            }
        }
    }

    public Result leaveRoom(UserInRoom userInRoom){
        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        User user = userRepository.findById(userInRoom.getUserId()).get();
        if(room == null)
            return new Result("房间无效", 0);
        else {
            boolean found = false;
            int campId = 0;
            for(UserInRoom userInRoom1 : room.getUsers())
            {
                if(userInRoom1.getUserId().equals(userInRoom.getUserId()))
                {
                    campId = userInRoom1.getCampId() - 1; // 阵营编号需要 - 1
                    found = true;
                }
            }
            if(found)
            {
                room.getCamps().get(campId).leaveCamp(userInRoom);
                room.deleteUser(userInRoom.getUserId());
                roomRepository.save(room);

                user.setState(0);
                userRepository.save(user);
                return new Result("操作成功", 1);
            }
            else
            {
                return new Result("操作失败", 0);
            }

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
        //if(room.getRoomType().equals(RoomType.ROOM_SCORE) || room.getOwnerId().equals(userInRoom.getUserId())){
            Set<UserInRoom> users = room.getUsers();
            for(UserInRoom user: users){
                if(user.getState() == 0){
                    return new Result("有玩家未准备，请稍后重试", 0);
                }
            }
            List<Camp> camps = room.getCamps();
            for(Camp camp : camps)
            {
                if(camp.getUserNum() == 0)
                {
                    return new Result("阵营人数不能为 0， 请稍后重试", 0);
                }
            }

            String userInfoToSend = "";
            for(UserInRoom user: users){
                user.setState(2);
                user.setBeginTime(date);
                user.setGameId(room.getGameId());
                user.setRoomType(room.getRoomType());

                User userDetail = userRepository.findById(user.getUserId()).get();

                userInfoToSend += user.getUserId() + " " + user.getCampId() + " " + userDetail.getUserIp() + " " + "8080" + " " + "1" + " " + user.getWeaponId() + " " + user.getWeaponId() + "\n";
            }

            //room.setUsers(users);
            room.setState(1);

            GameOverview gameOverview = new GameOverview(room.getIp(), date, room.getRoomType(), room.getUsers());
            // 战斗号
            gameOverview.setGameId(room.getGameId());

            // 给战斗服务器发消息
            String roomInfoToSend = room.getId() + " " + room.getMaxPlayerNum() + " "  + room.getFrequency() + " " + "0" + " " + room.getDigitalRoomType() + " " + room.getPlayerNum() + " "  + room.getGameId() + "\n";
            String send = roomInfoToSend + userInfoToSend;

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> request = new HttpEntity<>(send);//将对象装入HttpEntity中

            try{
                String info = restTemplate.postForObject("http://10.0.0.24:30604", request, String.class);
                //String info = restTemplate.postForObject("http://202.120.40.8:30604", request, String.class);
            }catch (Exception e)
            {
                System.out.println("发送房间信息包出错：" + e.toString());
                return new Result("战斗通讯服务器出错，请稍后重试", 0);
            }

            if(room.getRoomType() == RoomType.ROOM_AI)
            {
                // 成功开始一次增加一次房间已训练值
                int trainedNum = room.getTrainedNum() + 1;
                room.setTrainedNum(trainedNum);
            }

            roomRepository.save(room);
            gameOverviewRepository.insert(gameOverview);

            return new Result("操作成功，即将开始游戏......", 1);
        //}
//        else{
//            return new Result("没有权限", 0);
//        }
    }

    // 对局中需要实时修改房间内用户状态
    public Result changeScore(UserInRoom userInRoom){
        Room room = roomRepository.findById(userInRoom.getRoomId()).get();
        for(UserInRoom userInRoom1 : room.getUsers())
        {
            if(userInRoom1.getUserId().equals(userInRoom.getUserId()))
            {
                userInRoom.setCampId(userInRoom1.getCampId());
                room.changeUser(userInRoom);
                roomRepository.save(room);
                return new Result("Ok", 1);
            }
        }
        return new Result("没有该用户", 1);
    }

    public Result endGame(Room room){
        System.out.println("begin end");

        Room room1 = roomRepository.findById(room.getId()).get();
        if(room1 == null)
            return new Result("room id wrong", 0);

        // 写入对局总览
        Date date = new Date();
        GameOverview gameOverview = gameOverviewRepository.getByGameId(room1.getGameId());

        int winUserId = room.getWinUserId();
        int winCampId = room.getWinCampId();

//        if(gameOverview == null)
//            return new Result("game id wrong", 0);

        if(gameOverview != null)
          gameOverview.setEndTime(date);

        // 存储每个用户的历史记录
        for(UserInRoom userInRoom: room1.getUsers()){
            if(userInRoom.getCampId() == winCampId)
            {
                userInRoom.setResult(1); // 在胜利阵营即为胜利
                userInRoom.setScore(10); // 胜利 +10分
                userInRoom.setDestroyNum(1); // 击毁数 +1
            }
            else
            {
                userInRoom.setResult(0); // 不在胜利阵营即为失败
                userInRoom.setScore(0); // 失败 0分
                userInRoom.setCrashNum(1); // 坠毁数 +1
            }

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

        if(gameOverview != null)
        {
            gameOverview.setUserInRoom(room1.getUsers());
            gameOverviewRepository.save(gameOverview);
        }

        // 如果是正常结束游戏，需要检查训练次数，不清空房间
        if(room.getForceEnd() == 0)
        {
            // 人机训练场如果训练次数没有达到预设值，需要更新房间状态继续
            if(room1.getRoomType() == RoomType.ROOM_AI && room1.getTrainedNum() < room1.getMaxTrainNum())
            {
                // 恢复房间状态,需要设置房间状态和游戏id
                room1.setState(0);
                long gameId = sequenceGenerator.generateSequence(GameOverview.SEQUENCE_NAME);
                room1.setGameId(Long.toString(gameId));
                // 房间内玩家需要清空分数、击毁数等等
                for(UserInRoom userInRoom1 : room1.getUsers())
                {
                    userInRoom1.clearSelf();
                }
                for(int i = 0; i < room1.getCampNum(); i++)
                {
                    for(UserInRoom userInRoom2 : room1.getCamps().get(i).getUsersInCamp())
                    {
                        userInRoom2.clearSelf();
                    }
                }

                //room1.setGameId(UUID.randomUUID().toString());
                // 保存房间状态，准备开始下一局
                roomRepository.save(room1);

                beginGame(room1.getUsers().iterator().next());
            }
            // 训练次数达到之后删除该房间
            else
            {
                // 结束游戏以后直接删除该房间
                roomRepository.deleteById(room.getId());
            }
        }
        // 如果是强制结束游戏，需要检查训练次数和清空房间
        else
        {
            if(room1.getRoomType() == RoomType.ROOM_AI && room1.getTrainedNum() < room1.getMaxTrainNum())
            {
                // 恢复房间状态
                room1.setGameId("");
                room1.setPlayerNum(0);
                room1.setSpectatorsNum(0);
                room1.setState(0);
                room1.getUsers().clear();
                room1.getSpectators().clear();
                room1.getCamps().clear();
                // 初始化阵营
                List<Camp> camps = new LinkedList<>();
                for(int i = 0; i < room1.getCampNum(); i++)
                {
                    Camp camp = new Camp(i + 1, room1.getEachCampPlayNum()); // camp id + 1，从 1 开始
                    camps.add(camp);
                }
                room1.setCamps(camps);

                room1.getExchangeCamps().clear();

                long gameId = sequenceGenerator.generateSequence(GameOverview.SEQUENCE_NAME);
                room1.setGameId(Long.toString(gameId));

                roomRepository.save(room1);
            }
            // 训练次数达到之后删除该房间
            else
            {
                // 结束游戏以后直接删除该房间
                roomRepository.deleteById(room.getId());
            }
        }




        System.out.println("finish end");

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