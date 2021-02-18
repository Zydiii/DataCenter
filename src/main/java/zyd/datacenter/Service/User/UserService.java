package zyd.datacenter.Service.User;

import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Result;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUser();

    public Result insertUser(User user);

    public Result deleteUser(String id);

    public Result updateUser(User user);

    public Optional<User> findUser(String id);
}
