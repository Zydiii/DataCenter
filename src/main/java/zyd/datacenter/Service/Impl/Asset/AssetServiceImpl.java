package zyd.datacenter.Service.Impl.Asset;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.AnnounceBoard.AnnouncementBoard;
import zyd.datacenter.Entities.Asset.Asset;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Asset.AssetRepositoryRepository;
import zyd.datacenter.Service.Asset.AssetService;

import java.util.Date;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    private AssetRepositoryRepository assetRepositoryRepository;

    public AssetServiceImpl(AssetRepositoryRepository assetRepositoryRepository) {
        this.assetRepositoryRepository = assetRepositoryRepository;
    }

    public Result setNewAsset(Asset asset) {
        Date date = new Date();
        asset.setDate(date);
        assetRepositoryRepository.insert(asset);
        return new Result("操作成功", 1);
    }

    public Asset getLatestAsset() {
        List<Asset> assets = assetRepositoryRepository.findAll();
        if(assets.isEmpty())
            return null;
        else
            return assets.get(assets.size() - 1);
    }
}
