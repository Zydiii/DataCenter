package zyd.datacenter.Service.Impl.User;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Service.User.UserService;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Result insertUser(User user){
        if (userRepository.existsByUsername(user.getUsername()))
            return new Result("该用户名已存在",0);
        else if (userRepository.existsByEmail(user.getEmail()))
            return new Result("该邮箱已被注册",0);
        else if(userRepository.existsByPhone(user.getPhone()))
            return new Result("该手机号已被注册",0);
        else{
            userRepository.insert(user);
            return new Result("成功创建用户", 1) ;
        }
    }

    public Result deleteUser(String id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return new Result("成功删除该用户", 1);
        }
        else{
            return new Result("该用户不存在", 0);
        }
    }

    public Result updateUser(User user){
        if (userRepository.existsById(user.getId())){
            userRepository.save(user);
            return new Result("成功更新该用户", 1);
        }
        else{
            return new Result("该用户不存在", 0);
        }
    }

    public Optional<User> findUser(String id){
        return userRepository.findById(id);
    }

    public Result addPhoto(String userId, MultipartFile file)
    {
        User user = userRepository.findById(userId).get();
        try {
            user.setAvatar(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            user.setAvatarBase(Base64.getEncoder().encodeToString(user.getAvatar().getData()));
        }
        catch (Exception e)
        {
            return new Result(e.toString(), 0);
        }
        userRepository.save(user);
        return new Result( Base64.getEncoder().encodeToString(user.getAvatar().getData()), 1);
    }

    public Result getPhoto(String userId)
    {
        User user  = userRepository.findById(userId).get();
        return new Result(Base64.getEncoder().encodeToString(user.getAvatar().getData()), 1);
    }

}
