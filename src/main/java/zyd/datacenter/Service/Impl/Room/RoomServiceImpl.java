package zyd.datacenter.Service.Impl.Room;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Room.Room;
import zyd.datacenter.Entities.Room.RoomType;
import zyd.datacenter.Entities.User.Spectator;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Entities.User.UserInRoom;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Room.RoomRepository;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Service.Room.RoomService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

    private UserRepository userRepository;

    public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public List<Room> getAllRoom(){
        return roomRepository.findAll();
    }

    public Result createRoom(Room room){
        room.setIp(allocatRoom());
        User user = userRepository.findById(room.getOwnerId()).get();
        room.setOwnerUsername(user.getUsername());
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
            user.setState(1);
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
            }
            room.setUsers(users);
            room.setState(1);
            roomRepository.save(room);
            return new Result("操作成功，即将开始游戏......", 1);
        }
        else{
            return new Result("没有权限", 0);
        }
    }
}