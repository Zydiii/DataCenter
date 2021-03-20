package zyd.datacenter.Repository.Rank;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Rank.RankType;
import zyd.datacenter.Entities.Rank.Ranks;

public interface RanksRepository extends MongoRepository<Ranks, String> {
    Ranks findByRankType(RankType rankType);
}
