package zyd.datacenter.Repository.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.User.UserScore;

public interface UserScoreRepository extends MongoRepository<UserScore, String> {
}
