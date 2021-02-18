package zyd.datacenter.Service.Impl.Rank;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Rank.WeekRankRepository;
import zyd.datacenter.Service.Rank.WeekRankService;

@Service
public class WeekRankServiceImpl implements WeekRankService {
    private WeekRankRepository weekRankRepository;

    public WeekRankServiceImpl(WeekRankRepository weekRankRepository) {
        this.weekRankRepository = weekRankRepository;
    }
}
