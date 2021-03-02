package zyd.datacenter.Service.Impl.Rank;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.Rank.Rank;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Repository.Rank.RankRepository;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Service.Rank.RankService;

import java.util.Comparator;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    private UserRepository userRepository;

    private RankRepository rankRepository;

    public RankServiceImpl(UserRepository userRepository, RankRepository rankRepository) {
        this.userRepository = userRepository;
        this.rankRepository = rankRepository;
    }

    public List<Rank> getRank(){
        return rankRepository.findAll();
    }

    public void Rank(){
        List<User> users = userRepository.findAll();
        users.sort(comparatorUserScore);
        rankRepository.deleteAll();
        users.forEach(user -> {
            Rank rank = new Rank(user.getUsername(), user.getScore());
            rankRepository.save(rank);
        });
    }

    // 分数比较器
    Comparator<User> comparatorUserScore = new Comparator <User>(){
        public int compare(User user1,User user2){
            if (user1.getScore() < user2.getScore())
                return 1;
            else if (user1.getScore() > user2.getScore())
                return -1;
            else
                return 0;
        }
    };
}
