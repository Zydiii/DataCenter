package zyd.datacenter.Entities.Room;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.User.Spectator;
import zyd.datacenter.Entities.User.UserInRoom;

import java.util.HashSet;
import java.util.Set;

@Document(value = "Room")
public class Room {
    @Id
    private String id;

    private String ip; // 房间地址

    private int state; // 房间状态，0表示房间空闲，1表示房间内在进行游戏

    private int playerNum; // 房间当前人数

    private int maxPlayerNum; // 房间最大人数

    private Set<UserInRoom> users = new HashSet<>(); // 房间内玩家

    private int campNum; // 队伍数量

    private String environmentId; // 房间内环境编号

    private String gameId; // 当前房间战斗号

    private String ownerId; // 房间创建者编号

    private int maxSpectatorsNum; // 最大观战者人数

    private int spectatorsNum; // 当前观战者人数

    private Set<Spectator> spectators; // 观战用户

    public Room(String ip, int maxPlayerNum, int campNum, String environmentId, String ownerId, int maxSpectatorsNum) {
        this.ip = ip;
        this.state = 0;
        this.playerNum = 0;
        this.maxPlayerNum = maxPlayerNum;
        this.campNum = campNum;
        this.environmentId = environmentId;
        this.ownerId = ownerId;
        this.maxSpectatorsNum = maxSpectatorsNum;
        this.spectatorsNum = 0;

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

    public void addUser(UserInRoom userInroom){
        this.users.add(userInroom);
        this.playerNum++;
    }

    public void deleteUser(UserInRoom userInRoom){
        this.users.remove(userInRoom);
        this.playerNum--;
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
}
