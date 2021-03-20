package zyd.datacenter.Entities.Rank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(value = "AllRank")
public class AllRank {
    @Id
    private String id;

    private String username; // 用户名

    @Indexed(direction = IndexDirection.DESCENDING)
    private float score; // 按照降序存储玩家分数

    @Version
    private Long version;
}
