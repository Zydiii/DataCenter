package zyd.datacenter.Entities.Game;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.Room.RoomType;
import zyd.datacenter.Entities.User.UserInRoom;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(value = "GameOverview")
public class GameOverview {
    @Id
    private String id;

    private String gameId; // 战斗号

    private String roomIp; // 战斗所在的房间地址

    private Date beginTime; // 开始时间

    private Date endTime; // 结束时间

    private RoomType type; // 战斗类型

    private Set<UserInRoom> userInRoom = new HashSet<>(); // 战斗参与的用户

    public GameOverview(String roomIp, Date beginTime, RoomType type, Set<UserInRoom> userInRoom) {
        this.roomIp = roomIp;
        this.beginTime = beginTime;
        this.type = type;
        this.userInRoom = userInRoom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomIp() {
        return roomIp;
    }

    public void setRoomIp(String roomIp) {
        this.roomIp = roomIp;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Set<UserInRoom> getUserInRoom() {
        return userInRoom;
    }

    public void setUserInRoom(Set<UserInRoom> userInRoom) {
        this.userInRoom = userInRoom;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
