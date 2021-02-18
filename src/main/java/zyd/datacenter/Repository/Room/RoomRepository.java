package zyd.datacenter.Repository.Room;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Room.Room;

public interface RoomRepository extends MongoRepository<Room, String> {

}
