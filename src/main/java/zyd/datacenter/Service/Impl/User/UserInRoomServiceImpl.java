package zyd.datacenter.Service.Impl.User;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.User.UserInRoomRepository;
import zyd.datacenter.Service.User.UserInRoomService;

@Service
public class UserInRoomServiceImpl implements UserInRoomService {
    private UserInRoomRepository userInRoomRepository;

    public UserInRoomServiceImpl(UserInRoomRepository userInRoomRepository) {
        this.userInRoomRepository = userInRoomRepository;
    }
}
