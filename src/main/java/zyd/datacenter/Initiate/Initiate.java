package zyd.datacenter.Initiate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zyd.datacenter.Entities.Rank.RankType;
import zyd.datacenter.Service.Rank.RankService;

import java.util.*;

@Component
public class Initiate implements CommandLineRunner {
//    private Timer timer;
//
    @Autowired
    private RankService rankService;

    @Override
    public void run(String... args) throws Exception {
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




