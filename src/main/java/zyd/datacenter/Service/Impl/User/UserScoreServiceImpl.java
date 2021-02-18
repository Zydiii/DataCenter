package zyd.datacenter.Service.Impl.User;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.User.UserScoreRepository;
import zyd.datacenter.Service.User.UserScoreService;

@Service
public class UserScoreServiceImpl implements UserScoreService {
    private UserScoreRepository userScoreRepository;

    public UserScoreServiceImpl(UserScoreRepository userScoreRepository) {
        this.userScoreRepository = userScoreRepository;
    }
}
