package zyd.datacenter.Repository.Rank;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Rank.RankSetting;

public interface RankSettingRepository extends MongoRepository<RankSetting, String> {
}
