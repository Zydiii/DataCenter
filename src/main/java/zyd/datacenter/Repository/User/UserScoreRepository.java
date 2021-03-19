package zyd.datacenter.Repository.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.User.UserScore;

import java.util.List;

public interface UserScoreRepository extends MongoRepository<UserScore, String> {
    List<UserScore> getAllByTimestampBetween(long startTime, long endTime);
}
