package zyd.datacenter.Repository.Rank;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Rank.DayRank;

public interface DayRankRepository extends MongoRepository<DayRank, String> {
}
