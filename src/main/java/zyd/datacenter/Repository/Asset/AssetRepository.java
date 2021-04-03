package zyd.datacenter.Repository.Asset;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Asset.Asset;

public interface AssetRepository extends MongoRepository<Asset, String> {
}
