package zyd.datacenter.Repository.AnnounceBoard;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.AnnounceBoard.AnnouncementBoard;

public interface AnnounceBoardRepository extends MongoRepository<AnnouncementBoard, String> {

}
