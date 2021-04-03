package zyd.datacenter.Service.Impl.Asset;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Asset.Asset;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Asset.AssetRepository;
import zyd.datacenter.Service.Asset.AssetService;

import java.util.Date;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    private AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Result setNewAsset(Asset asset) {
        Date date = new Date();
        asset.setDate(date);
        assetRepository.insert(asset);
        return new Result("操作成功", 1);
    }

    public Asset getLatestAsset() {
        List<Asset> assets = assetRepository.findAll();
        if(assets.isEmpty())
            return null;
        else
            return assets.get(assets.size() - 1);
    }
}
