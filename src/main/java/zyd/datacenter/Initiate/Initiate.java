package zyd.datacenter.Initiate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zyd.datacenter.Entities.Asset.Asset;
import zyd.datacenter.Entities.Chat.Channel;
import zyd.datacenter.Entities.Rank.RankType;
import zyd.datacenter.Entities.Rank.Ranks;
import zyd.datacenter.Entities.User.AvatarHelper;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Repository.Asset.AssetRepository;
import zyd.datacenter.Repository.Chat.ChannelRepository;
import zyd.datacenter.Repository.Rank.RanksRepository;
import zyd.datacenter.Repository.User.UserRepository;

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

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private AssetRepository assetRepository;

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
            User user = new User("test0", "123456", "123456", "1", "1", new HashSet<String>(), new HashSet<String>(), new HashSet<String>());
            String avatar = "";
            try{
                avatar = AvatarHelper.createBase64Avatar(Math.abs("default".hashCode()));
            }
            catch (Exception e)
            {

            }
            user.setAvatarBase(avatar);

            userRepository.insert(user);
        }
    }

    // 初始化公共聊天频道
    private void initiateChannel()
    {
        if(channelRepository.findAll().isEmpty())
        {
            channelRepository.insert(new Channel("公共频道1"));
            channelRepository.insert(new Channel("公共频道2"));
            channelRepository.insert(new Channel("公共频道3"));
        }
    }

    // 初始化版本
    private void initiateAsset()
    {
        if(assetRepository.findAll().isEmpty())
        {
            assetRepository.insert(new Asset("0.0.1", "", ""));
        }
    }

    @Override
    public void run(String... args) throws Exception {

        // 初始化排行榜数据库
        initiateRanks();

        // 初始化玩家数据库
        initiateTestUser();

        initiateChannel();
        initiateAsset();



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




