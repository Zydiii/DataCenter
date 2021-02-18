package zyd.datacenter.Service.AnnounceBoard;

import zyd.datacenter.Entities.AnnounceBoard.AnnounceBoard;
import zyd.datacenter.Payload.Result;

public interface AnnounceBoardService {
    public Result writeAnnounce(AnnounceBoard announceBoard);

    public AnnounceBoard getLatestAnnounce();
}
