package zyd.datacenter.Entities.Rank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.User.UserScore;

import java.util.LinkedList;
import java.util.List;

@Document("Ranks")
@ApiModel(value="Ranks", description="排行榜数据，存储不同类型排行榜的数据")
public class Ranks {
    @Id
    private String id;

    @ApiModelProperty(value = "排行类型，RANK_ALL->总榜，RANK_DAY->日榜，RANK_WEEK->周榜，RANK_MONTH->月榜")
    private RankType rankType;

    @ApiModelProperty(value = "用户得分列表，从高分到低分进行了排序")
    private List<UserScore> userScoreList = new LinkedList<UserScore>();

    @Version
    private Long version;

    public Ranks(RankType rankType) {
        this.rankType = rankType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RankType getRankType() {
        return rankType;
    }

    public void setRankType(RankType rankType) {
        this.rankType = rankType;
    }

    public List<UserScore> getUserScoreList() {
        return userScoreList;
    }

    public void setUserScoreList(List<UserScore> userScoreList) {
        this.userScoreList = userScoreList;
    }
}
