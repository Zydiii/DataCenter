package zyd.datacenter.Initiate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zyd.datacenter.Service.Rank.RankService;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class Initiate implements CommandLineRunner {
    private Timer timer;

    @Autowired
    private RankService rankService;

    @Override
    public void run(String... args) throws Exception {
        timer = new Timer();
        TimerTask rankTimerTask = new TimerTask(){
            @Override
            public void run() {
                rankService.Rank();
                //System.out.println("rank");
            }
        };
        timer.schedule(rankTimerTask, 0, 600000);
    }
}


