package zyd.datacenter.Entities.Room;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.User.UserInRoom;

@Document(value = "ExchangeCamp")
public class ExchangeCamp {
    @Id
    private String id;

    @Version
    private long version;

    private UserInRoom fromUser;

    private UserInRoom toUser;

    private int state; // 状态，未回应 0， 已同意 1，已拒绝 2，取消 3

    private String roomId; // 房间号

    public ExchangeCamp(UserInRoom fromUser, UserInRoom toUser, int state, String roomId) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.state = state;
        this.roomId = roomId;
    }

    public UserInRoom getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserInRoom fromUser) {
        this.fromUser = fromUser;
    }

    public UserInRoom getToUser() {
        return toUser;
    }

    public void setToUser(UserInRoom toUser) {
        this.toUser = toUser;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
