package zyd.datacenter.Entities.Rank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "WeekRank")
public class WeekRank {
    @Id
    private String id;

    private String username; // 用户名

    @Indexed(direction = IndexDirection.DESCENDING)
    private float score; // 按照降序存储玩家分数
}
