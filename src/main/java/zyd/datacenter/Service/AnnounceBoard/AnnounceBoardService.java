package zyd.datacenter.Service.AnnounceBoard;

import zyd.datacenter.Entities.AnnounceBoard.AnnouncementBoard;
import zyd.datacenter.Payload.Result;

public interface AnnounceBoardService {
    public Result writeAnnounce(AnnouncementBoard announceBoard);

    public AnnouncementBoard getLatestAnnounce();
}
