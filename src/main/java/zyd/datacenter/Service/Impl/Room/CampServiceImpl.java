package zyd.datacenter.Service.Impl.Room;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Room.CampRepository;
import zyd.datacenter.Service.Room.CampService;

@Service
public class CampServiceImpl implements CampService {
    private CampRepository campRepository;

    public CampServiceImpl(CampRepository campRepository) {
        this.campRepository = campRepository;
    }
}
