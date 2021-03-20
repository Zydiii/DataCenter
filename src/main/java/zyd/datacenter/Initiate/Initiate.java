package zyd.datacenter.Initiate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zyd.datacenter.Entities.Rank.RankType;
import zyd.datacenter.Entities.Rank.Ranks;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Repository.Rank.RanksRepository;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Service.Rank.RankService;

import java.util.*;

@Component
public class Initiate implements CommandLineRunner {
//    private Timer timer;
//
//    @Autowired
//    private RankService rankService;

    @Autowired
    private RanksRepository ranksRepository;

    @Autowired
    private UserRepository userRepository;

    // 初始化排行榜数据库
    private void initiateRanks()
    {
        if(ranksRepository.findAll().isEmpty())
        {
            ranksRepository.insert(new Ranks(RankType.RANK_ALL));
            ranksRepository.insert(new Ranks(RankType.RANK_DAY));
            ranksRepository.insert(new Ranks(RankType.RANK_WEEK));
            ranksRepository.insert(new Ranks(RankType.RANK_MONTH));
        }
    }

    // 初始化玩家数据库
    private void initiateTestUser()
    {
        if(userRepository.findAll().isEmpty())
        {
            userRepository.insert(new User("test0", "123456", "123456", "1", "1", new HashSet<String>(), new HashSet<String>(), new HashSet<String>()));
            userRepository.insert(new User("test1", "123456", "123456", "1", "1", new HashSet<String>(), new HashSet<String>(), new HashSet<String>()));
            userRepository.insert(new User("test2", "123456", "123456", "1", "1", new HashSet<String>(), new HashSet<String>(), new HashSet<String>()));
            userRepository.insert(new User("test3", "123456", "123456", "1", "1", new HashSet<String>(), new HashSet<String>(), new HashSet<String>()));
            userRepository.insert(new User("test4", "123456", "123456", "1", "1", new HashSet<String>(), new HashSet<String>(), new HashSet<String>()));
        }
    }

    @Override
    public void run(String... args) throws Exception {

        // 初始化排行榜数据库
        initiateRanks();

        // 初始化玩家数据库
        initiateTestUser();

        //rankService.test();
        // 总榜
        //rankService.allRankScheduler();
//        timer = new Timer();
//        TimerTask rankTimerTask = new TimerTask(){
//            @Override
//            public void run() {
//                rankService.Rank(RankType.RANK_ALL);
//                //System.out.println("rank");
//            }
//        };
//        timer.schedule(rankTimerTask, 0, 60000);

        // 周榜
        //rankService.weekRankScheduler();
    }


}




