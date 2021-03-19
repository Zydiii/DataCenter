package zyd.datacenter.Service.Impl.User;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Entities.User.UserScore;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Repository.User.UserScoreRepository;
import zyd.datacenter.Service.User.UserScoreService;

import java.util.Date;

@Service
public class UserScoreServiceImpl implements UserScoreService {
    private UserScoreRepository userScoreRepository;

    private UserRepository userRepository;

    public UserScoreServiceImpl(UserScoreRepository userScoreRepository, UserRepository userRepository) {
        this.userScoreRepository = userScoreRepository;
        this.userRepository = userRepository;
    }

    public Result addUserScore(UserScore userScore){
        Date date = new Date();
        userScore.setGetScoreDate(date);
        userScore.setTimestamp(date.getTime());
        User user = userRepository.findById(userScore.getUserId()).get();
        userScore.setUsername(user.getUsername());
        userScoreRepository.insert(userScore);
        return new Result("OK", 1);
    }


}
