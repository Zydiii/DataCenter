package zyd.datacenter.Service.Impl.Rank;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Rank.MonthRankRepository;
import zyd.datacenter.Service.Rank.MonthRankService;

@Service
public class MonthRankServiceImpl implements MonthRankService {
    private MonthRankRepository monthRankRepository;

    public MonthRankServiceImpl(MonthRankRepository monthRankRepository) {
        this.monthRankRepository = monthRankRepository;
    }
}
