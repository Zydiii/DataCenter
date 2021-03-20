package zyd.datacenter.Entities.User;

import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Spectator")
@ApiModel(value="Spectator", description="观战者数据，目前没有用到")
public class Spectator {
    @Id
    private String id;

    private String userId; // 用户编号

    private String roomId; // 房间编号

    @Version
    private Long version;

    public Spectator(String id, String userId, String roomId) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
