package zyd.datacenter.Service.Impl.Environment;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Environment.MapRepository;
import zyd.datacenter.Service.Environment.MapService;

@Service
public class MapServiceImpl implements MapService {
    private MapRepository mapRepository;

    public MapServiceImpl(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }
}
