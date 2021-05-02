package zyd.datacenter.Entities.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.Room.RoomType;
import zyd.datacenter.Repository.User.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Document(value = "UserInRoom")
@ApiModel(value="UserInRoom", description="房间内玩家数据，存储房间内玩家的详细数据")
public class UserInRoom {
    @Id
    private String id;

    private String avatar;

    @ApiModelProperty(value = "战斗编号")
    private String gameId; // 战斗编号

    @ApiModelProperty(value = "房间类型，ROOM_FREE->练习场，ROOM_SCORE->正式场，ROOM_AI->人机场")
    private RoomType roomType; // 房间类型

    @ApiModelProperty(value = "房间编号")
    private String roomId; // 房间编号

    @ApiModelProperty(value = "玩家用户名")
    private String username; // 玩家用户名

    @ApiModelProperty(value = "开始时间")
    private Date beginTime; // 开始时间

    @ApiModelProperty(value = "开始北京时间")
    private String bjBeginTime; // 开始北京时间

    @ApiModelProperty(value = "开始时间戳")
    private long beginTimestamp; // 开始时间戳

    @ApiModelProperty(value = "结束时间")
    private Date endTime; // 结束时间

    @ApiModelProperty(value = "结束北京时间")
    private String bjEndTime; // 结束北京时间

    @ApiModelProperty(value = "结束时间戳")
    private long endTimestamp; // 结束时间戳

    @ApiModelProperty(value = "玩家编号")
    private String userId; // 玩家编号

    @ApiModelProperty(value = "玩家状态，0->未准备好，1->准备好等待开始，2->在进行游戏")
    private int state; // 玩家状态，0表示未准备好，1表示准备好等待开始，2表示在进行游戏

    @ApiModelProperty(value = "玩家当前所在阵营编号")
    private int campId; // 玩家当前所在阵营编号，从 0->N-1编号

    @ApiModelProperty(value = "玩家当前所在阵营名称")
    public String campName; // 玩家所在的阵营名称

    public int campState = 0; // 状态，非交换中 0，交换中 1

    @ApiModelProperty(value = "玩家选择的武器编号")
    private String weaponId; // 玩家选择的武器编号

    private String weaponName;

    @ApiModelProperty(value = "玩家获得的分数")
    private float score; // 玩家获得的分数

    @ApiModelProperty(value = "玩家的总伤害")
    private float damageValue; // 玩家的总伤害

    @ApiModelProperty(value = "击毁数")
    private int destroyNum; // 击毁数

    @ApiModelProperty(value = "坠毁数")
    private int crashNum; // 坠毁数

    @ApiModelProperty(value = "战斗结果，0->失败，1->胜利，2->平局")
    private int result; // 0代表失败，1代表胜利，2代表平局

    @Version
    private Long version;

    public UserInRoom(String roomId, String userId)
    {
        this.roomId = roomId;
        this.userId = userId;
    }

    public void clearSelf()
    {
        this.score = 0;
        this.crashNum = 0;
        this.damageValue = 0;
        this.destroyNum = 0;
        this.result = 0;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public String getBjBeginTime() {
        return bjBeginTime;
    }

    public void setBjBeginTime(String bjBeginTime) {
        this.bjBeginTime = bjBeginTime;
    }

    public long getBeginTimestamp() {
        return beginTimestamp;
    }

    public void setBeginTimestamp(long beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    public String getBjEndTime() {
        return bjEndTime;
    }

    public void setBjEndTime(String bjEndTime) {
        this.bjEndTime = bjEndTime;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
        this.beginTimestamp = beginTime.getTime();
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        this.bjBeginTime = bjSdf.format(beginTime);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        this.endTimestamp = endTime.getTime();
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        this.bjEndTime = bjSdf.format(endTime);
    }

    //    public UserInRoom(String roomId, String userId, String campId, String weaponId, float score, float damageValue, int destroyNum, int crashNum, int result) {
//        this.roomId = roomId;
//        this.userId = userId;
//        this.state = 0;
//        this.campId = campId;
//        this.weaponId = weaponId;
//        this.score = score;
//        this.damageValue = damageValue;
//        this.destroyNum = destroyNum;
//        this.crashNum = crashNum;
//        this.result = result;
//    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public String getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(String weaponId) {
        this.weaponId = weaponId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getDamageValue() {
        return damageValue;
    }

    public void setDamageValue(float damageValue) {
        this.damageValue = damageValue;
    }

    public int getDestroyNum() {
        return destroyNum;
    }

    public void setDestroyNum(int destroyNum) {
        this.destroyNum = destroyNum;
    }

    public int getCrashNum() {
        return crashNum;
    }

    public void setCrashNum(int crashNum) {
        this.crashNum = crashNum;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}