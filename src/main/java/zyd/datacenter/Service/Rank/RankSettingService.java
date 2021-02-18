package zyd.datacenter.Service.Rank;

import zyd.datacenter.Entities.Rank.RankSetting;
import zyd.datacenter.Payload.Result;

public interface RankSettingService {
    public Result setRule(RankSetting rankSetting);

    public RankSetting getSetting();
}
