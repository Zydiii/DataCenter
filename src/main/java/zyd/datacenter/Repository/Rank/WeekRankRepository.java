package zyd.datacenter.Repository.Rank;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Rank.WeekRank;

public interface WeekRankRepository extends MongoRepository<WeekRank, String> {
}
