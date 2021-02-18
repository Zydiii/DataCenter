package zyd.datacenter.Repository.Rank;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Rank.MonthRank;

public interface MonthRankRepository extends MongoRepository<MonthRank, String> {
}
