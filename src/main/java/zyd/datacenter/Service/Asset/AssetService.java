package zyd.datacenter.Service.Asset;

import zyd.datacenter.Entities.Asset.Asset;
import zyd.datacenter.Payload.Result;

public interface AssetService {
    public Result setNewAsset(Asset asset);
    public Asset getLatestAsset();
}
