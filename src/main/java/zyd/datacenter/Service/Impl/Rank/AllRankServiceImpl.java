package zyd.datacenter.Service.Impl.Rank;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Rank.AllRankRepository;
import zyd.datacenter.Service.Rank.AllRankService;

@Service
public class AllRankServiceImpl implements AllRankService {
    private AllRankRepository allRankRepository;

    public AllRankServiceImpl(AllRankRepository allRankRepository) {
        this.allRankRepository = allRankRepository;
    }
}
