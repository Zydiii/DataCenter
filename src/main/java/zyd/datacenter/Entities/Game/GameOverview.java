package zyd.datacenter.Entities.Game;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.Room.RoomType;
import zyd.datacenter.Entities.User.UserInRoom;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(value = "GameOverview")
@ApiModel(value="GameOverview", description="战斗总览数据，存储每场战斗的总览数据，参战用户的具体数据也在此")
public class GameOverview {
    @Id
    private String id;

    @ApiModelProperty(value = "战斗号")
    private String gameId; // 战斗号

    @ApiModelProperty(value = "战斗所在的房间地址，目前战斗服务器IP固定，所以可以不用设置，直接写死")
    private String roomIp; // 战斗所在的房间地址

    @ApiModelProperty(value = "战斗开始时间")
    private Date beginTime; // 开始时间

    @ApiModelProperty(value = "战斗结束时间")
    private Date endTime; // 结束时间

    @ApiModelProperty(value = "战斗类型，ROOM_FREE->练习场，ROOM_SCORE->正式场，ROOM_AI->人机场")
    private RoomType type; // 战斗类型

    @ApiModelProperty(value = "战斗参与的用户，存储用户具体得分情况")
    private Set<UserInRoom> userInRoom = new HashSet<>(); // 战斗参与的用户

    @Version
    private Long version;

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
