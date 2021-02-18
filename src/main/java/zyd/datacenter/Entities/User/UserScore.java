package zyd.datacenter.Entities.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(value = "UserScore")
public class UserScore {
    @Id
    private String id;

    private String userId; // 玩家编号

    @Indexed(direction = IndexDirection.ASCENDING)
    private Date getScoreDate; // 获得积分的时间

    private float score; // 获得的积分

    public UserScore(String userId, Date getScoreDate, float score) {
        this.userId = userId;
        this.getScoreDate = getScoreDate;
        this.score = score;
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

    public Date getGetScoreDate() {
        return getScoreDate;
    }

    public void setGetScoreDate(Date getScoreDate) {
        this.getScoreDate = getScoreDate;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
