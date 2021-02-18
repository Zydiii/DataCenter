package zyd.datacenter.Service.Impl.Rank;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Rank.RankSetting;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Rank.RankSettingRepository;
import zyd.datacenter.Service.Rank.RankSettingService;

import java.util.List;

@Service
public class RankSettingServiceImpl implements RankSettingService {
    private RankSettingRepository rankSettingRepository;

    public RankSettingServiceImpl(RankSettingRepository rankSettingRepository) {
        this.rankSettingRepository = rankSettingRepository;
    }

    public Result setRule(RankSetting rankSetting){
        rankSettingRepository.insert(rankSetting);
        return new Result("操作成功", 1);
    }

    public RankSetting getSetting(){
        List<RankSetting> settings = rankSettingRepository.findAll();
        if(settings.isEmpty())
            return null;
        else
            return settings.get(settings.size() - 1);
    }
}
