package zyd.datacenter.Repository.Rank;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Rank.Rank;

public interface RankRepository extends MongoRepository<Rank, String> {
}
