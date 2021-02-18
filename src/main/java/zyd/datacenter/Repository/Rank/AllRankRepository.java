package zyd.datacenter.Repository.Rank;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Rank.AllRank;

public interface AllRankRepository extends MongoRepository<AllRank, String> {
}
