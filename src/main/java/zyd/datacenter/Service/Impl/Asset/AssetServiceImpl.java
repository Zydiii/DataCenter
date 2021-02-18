package zyd.datacenter.Service.Impl.Asset;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Asset.AssetRepositoryRepository;
import zyd.datacenter.Service.Asset.AssetService;

@Service
public class AssetServiceImpl implements AssetService {
    private AssetRepositoryRepository assetRepositoryRepository;

    public AssetServiceImpl(AssetRepositoryRepository assetRepositoryRepository) {
        this.assetRepositoryRepository = assetRepositoryRepository;
    }
}
