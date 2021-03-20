package zyd.datacenter.Entities.Game;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.Room.RoomType;

@Document("GameHistory")
@ApiModel(value="GameHistory", description="历史战绩数据，存储玩家历史战绩数据总览")
public class GameHistory {
    @Id
    private String id;

    @ApiModelProperty(value = "玩家编号")
    private String userId; // 玩家编号

    @ApiModelProperty(value = "战斗编号")
    private String gameId; // 战斗编号

    @Version
    private Long version;

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
