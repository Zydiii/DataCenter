package zyd.datacenter.Entities.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "UserInRoom")
public class UserInRoom {
    @Id
    private String id;

    private String roomId; // 房间编号

    private String userId; // 玩家编号

    private int state; // 玩家状态，0表示未准备好，1表示准备好等待开始，2表示在进行游戏

    private String campId; // 玩家当前所在阵营编号

    private String weaponId; // 玩家选择的武器编号

    private float score; // 玩家获得的分数

    private float damageValue; // 玩家的总伤害

    private int destroyNum; // 击毁数

    private int crashNum; // 坠毁数

    private int result; // 0代表失败，1代表胜利，2代表平局

    public UserInRoom(String roomId, String userId, int state, String campId, String weaponId, float score, float damageValue, int destroyNum, int crashNum, int result) {
        this.roomId = roomId;
        this.userId = userId;
        this.state = state;
        this.campId = campId;
        this.weaponId = weaponId;
        this.score = score;
        this.damageValue = damageValue;
        this.destroyNum = destroyNum;
        this.crashNum = crashNum;
        this.result = result;
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

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
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
