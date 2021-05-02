package zyd.datacenter.Service.Room;

import com.mongodb.MongoCommandException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Entities.Room.Room;
import zyd.datacenter.Entities.Room.RoomType;
import zyd.datacenter.Entities.User.Spectator;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Entities.User.UserInRoom;
import zyd.datacenter.Payload.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoomService {
    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public List<Room> getAllRoom();

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public String getIp(HttpServletRequest request);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result joinCamp(UserInRoom userInRoom);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result createRoom(Room room);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result deleteRoom(Room questRoom);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result updateRoom(Room questRoom);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result joinRoom(UserInRoom userInRoom, HttpServletRequest request);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result leaveRoom(UserInRoom userInRoom);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result match(UserInRoom userInRoom);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result joinWatchRoom(Spectator spectator);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public List<Room> getRoom(RoomType roomType);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result readyInRoom(UserInRoom userInRoom);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result cancelReadyInRoom(UserInRoom userInRoom);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Room getOneRoom(String roomId);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result beginGame(UserInRoom userInRoom);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result endGame(Room room);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result changeScore(UserInRoom userInRoom);

}