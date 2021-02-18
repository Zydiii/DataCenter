package zyd.datacenter.Repository.AnnounceBoard;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.AnnounceBoard.AnnounceBoard;

import java.util.List;

public interface AnnounceBoardRepository extends MongoRepository<AnnounceBoard, String> {

}
