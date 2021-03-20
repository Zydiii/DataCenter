package zyd.datacenter.Service.User;

import com.mongodb.MongoCommandException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Result;

import java.util.List;
import java.util.Optional;

public interface UserService {
    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public List<User> getAllUser();

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result insertUser(User user);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result deleteUser(String id);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result updateUser(User user);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Optional<User> findUser(String id);
}
