package zyd.datacenter.Repository.Room;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Room.Room;
import zyd.datacenter.Entities.Room.RoomType;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findAllByRoomType(RoomType roomType);
}
