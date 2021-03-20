package zyd.datacenter.Entities.Rank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Rank")
public class Rank {
    @Id
    private String id;

    private String username; // 用户名

    @Indexed(direction = IndexDirection.DESCENDING)
    private float score; // 按照降序存储玩家分数

    @Version
    private Long version;


    public Rank(String username, float score) {
        this.username = username;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
