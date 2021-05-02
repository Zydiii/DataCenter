package zyd.datacenter.Entities.Room;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.User.Spectator;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Entities.User.UserInRoom;
import zyd.datacenter.Repository.User.UserRepository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Document(value = "Room")
@ApiModel(value="Room", description="房间数据，存储房间数据，包括房间内的用户数据")
public class Room {
    @Id
    private String id;

    @Transient
    public static final String SEQUENCE_NAME = "rooms_sequence";

    @ApiModelProperty(value = "房间地址，目前只有一个服务器可以写死")
    private String ip; // 房间地址

    @ApiModelProperty(value = "房间状态，0->空闲，1->房间内在进行游戏")
    private int state; // 房间状态，0表示房间空闲，1表示房间内在进行游戏

    @ApiModelProperty(value = "房间当前人数")
    private int playerNum; // 房间当前人数

    private int eachCampPlayNum; // 每个阵营人数

    @ApiModelProperty(value = "房间最大人数")
    private int maxPlayerNum; // 房间最大人数

    @ApiModelProperty(value = "房间内玩家详细数据")
    private Set<UserInRoom> users = new HashSet<>(); // 房间内玩家

    @ApiModelProperty(value = "队伍数量")
    private int campNum; // 队伍数量

    @ApiModelProperty(value = "房间内环境编号，目前没有用")
    private String environmentId; // 房间内环境编号

    private String environmentName;

    @ApiModelProperty(value = "房间当前战斗号，每次上一次战斗结束后会生成新的战斗号")
    private String gameId; // 当前房间战斗号

    @ApiModelProperty(value = "房间创建者编号，只有创建者能够操作房间")
    private String ownerId; // 房间创建者编号

    @ApiModelProperty(value = "房间创建者用户名")
    private String ownerUsername; // 房间创建者用户名

    @ApiModelProperty(value = "最大观战者人数")
    private int maxSpectatorsNum; // 最大观战者人数

    @ApiModelProperty(value = "当前观战者人数")
    private int spectatorsNum; // 当前观战者人数

    @ApiModelProperty(value = "观战用户")
    private Set<Spectator> spectators = new HashSet<>();  // 观战用户

    @ApiModelProperty(value = "更新频率")
    private int frequency; // 更新频率

    @ApiModelProperty(value = "房间类型，ROOM_FREE->练习场，ROOM_SCORE->正式场，ROOM_AI->人机场")
    private RoomType roomType; // 房间类型

    private int digitalRoomType; //人机场 0, 练习场 1, 正式场 2

    private List<Camp> camps = new LinkedList<>(); // 房间内所有阵营

    private List<ExchangeCamp> exchangeCamps = new LinkedList<>(); // 交换阵营请求列表

    private int maxTrainNum;

    private int trainedNum;

    private int winUserId;

    private int winCampId;

    private int forceEnd; // 0 -> 检查 trainNum，正常结束，1 -> 不检查 trainNUm，掉线

    @Version
    private Long version;

    public Room(String ip, int maxPlayerNum, int campNum, String environmentId, String ownerId, int maxSpectatorsNum, int frequency, RoomType roomType) {
        this.ip = ip;
        this.state = 0;
        this.playerNum = 0;
        this.maxPlayerNum = maxPlayerNum;
        this.campNum = campNum;
        this.environmentId = environmentId;
        this.ownerId = ownerId;
        this.maxSpectatorsNum = maxSpectatorsNum;
        this.spectatorsNum = 0;
        this.frequency = frequency;
        this.roomType = roomType;
        this.trainedNum = 0;
    }

    public int getForceEnd() {
        return forceEnd;
    }

    public void setForceEnd(int forceEnd) {
        this.forceEnd = forceEnd;
    }

    public void setDigitalRoomType(RoomType roomType)
    {
        if(roomType == RoomType.ROOM_FREE)
            this.digitalRoomType = 1;
        else if(roomType == RoomType.ROOM_AI)
            this.digitalRoomType = 0;
        else if(roomType == RoomType.ROOM_SCORE)
            this.digitalRoomType = 2;
    }

    public int getTrainedNum() {
        return trainedNum;
    }

    public void setTrainedNum(int trainedNum) {
        this.trainedNum = trainedNum;
    }

    public int getMaxTrainNum() {
        return maxTrainNum;
    }

    public void setMaxTrainNum(int maxTrainNum) {
        this.maxTrainNum = maxTrainNum;
    }

    public int getWinUserId() {
        return winUserId;
    }

    public void setWinUserId(int winUserId) {
        this.winUserId = winUserId;
    }

    public int getWinCampId() {
        return winCampId;
    }

    public void setWinCampId(int winCampId) {
        this.winCampId = winCampId;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public int getDigitalRoomType() {
        return digitalRoomType;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public int getMaxPlayerNum() {
        return maxPlayerNum;
    }

    public void setMaxPlayerNum(int maxPlayerNum) {
        this.maxPlayerNum = maxPlayerNum;
    }

    public Set<UserInRoom> getUsers() {
        return users;
    }

    public void setUsers(Set<UserInRoom> users) {
        this.users = users;
    }

    public int getCampNum() {
        return campNum;
    }

    public void setCampNum(int campNum) {
        this.campNum = campNum;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getEachCampPlayNum() {
        return eachCampPlayNum;
    }

    public void setEachCampPlayNum(int eachCampPlayNum) {
        this.eachCampPlayNum = eachCampPlayNum;
    }

    public void addUser(UserInRoom userInroom){
        boolean found = false;
        for(UserInRoom user : this.users)
        {
            if(user.getUserId().equals(userInroom.getUserId()))
            {
                found = true;
                break;
            }
        }
        if(found)
            return;
        this.users.add(userInroom);
        this.playerNum++;
    }

    public void changeUser(UserInRoom userInRoom){
        for(UserInRoom user: users){
            if(user.getUserId().equals(userInRoom.getUserId())){
                if(userInRoom.getScore() != 0)
                    user.setScore(user.getScore() + userInRoom.getScore());
                if(userInRoom.getDamageValue() != 0)
                    user.setDamageValue(user.getDamageValue() + userInRoom.getDamageValue());
                if(userInRoom.getCrashNum() != 0)
                    user.setCrashNum(user.getCrashNum() + userInRoom.getCrashNum());
                if(userInRoom.getDestroyNum() != 0)
                    user.setDestroyNum(user.getDestroyNum() + userInRoom.getDestroyNum());
                if(userInRoom.getResult() != 0)
                    user.setResult(userInRoom.getResult());
                // 阵营里面的用户状态也修改，虽然这样很奇怪。。。
                camps.get(userInRoom.getCampId()).changeUser(userInRoom);
                break;
            }
        }
    }

//    public void deleteUser(UserInRoom userInRoom){
//        this.users.remove(userInRoom);
//        this.playerNum--;
//    }

    public void deleteUser(String userId){
        for(UserInRoom userInRoom: users){
            if(userInRoom.getUserId().equals(userId)){
                users.remove(userInRoom);
                this.playerNum--;
                break;
            }
        }
    }

    public void changeUserState(String userId, int state){
        for(UserInRoom userInRoom: users){
            if(userInRoom.getUserId().equals(userId)){
                userInRoom.setState(state);
                camps.get(userInRoom.getCampId()).changeUserState(userInRoom);
                break;
            }
        }
    }

    public void addSpectator(Spectator spectator){
        this.spectators.add(spectator);
        this.spectatorsNum++;
    }

    public void deleteSpectator(Spectator spectator){
        this.spectators.remove(spectator);
        this.spectatorsNum--;
    }

    public int getMaxSpectatorsNum() {
        return maxSpectatorsNum;
    }

    public void setMaxSpectatorsNum(int maxSpectatorsNum) {
        this.maxSpectatorsNum = maxSpectatorsNum;
    }

    public int getSpectatorsNum() {
        return spectatorsNum;
    }

    public void setSpectatorsNum(int spectatorsNum) {
        this.spectatorsNum = spectatorsNum;
    }

    public Set<Spectator> getSpectators() {
        return spectators;
    }

    public void setSpectators(Set<Spectator> spectators) {
        this.spectators = spectators;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void leaveCamp(UserInRoom userInRoom)
    {
        int campId = userInRoom.getCampId();
        camps.get(campId).leaveCamp(userInRoom);
    }

    public void joinCamp(UserInRoom userInRoom)
    {
        int campId = userInRoom.getCampId();
        camps.get(campId).joinCamp(userInRoom);
    }

    public List<Camp> getCamps() {
        return camps;
    }

    public void setCamps(List<Camp> camps) {
        this.camps = camps;
    }

    public List<ExchangeCamp> getExchangeCamps() {
        return exchangeCamps;
    }

    public void setExchangeCamps(List<ExchangeCamp> exchangeCamps) {
        this.exchangeCamps = exchangeCamps;
    }
}