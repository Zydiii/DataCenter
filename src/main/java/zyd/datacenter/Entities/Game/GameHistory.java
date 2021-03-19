package zyd.datacenter.Entities.Game;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.Room.RoomType;

@Document("GameHistory")
public class GameHistory {
    @Id
    private String id;

    private String userId; // 玩家编号

    private String gameId; // 战斗编号

    public GameHistory(String userId, String gameId) {
        this.userId = userId;
        this.gameId = gameId;
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
