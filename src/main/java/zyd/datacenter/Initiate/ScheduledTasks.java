package zyd.datacenter.Initiate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.Mail.MailService;
import zyd.datacenter.Service.Rank.RankService;

@Component
public class ScheduledTasks {
    @Autowired
    RankService rankService;

    @Autowired
    MailService mailService;

    // 每30分钟进行总榜排序
    @Scheduled(cron = "0 */30 * * * ?")
    public void allRankScheduler() {
        rankService.allRankScheduler();
    }

    // 每天零点进行日榜排序
    @Scheduled(cron = "0 0 0 * * ?")
    public void dayRankScheduler() {
        rankService.dayRankScheduler();
    }

    // 每周一零点进行周榜排序
    @Scheduled(cron = "0 0 0 ? * MON")
    public void weekRankScheduler() {
        rankService.weekRankScheduler();
    }

    // 每月一号零点进行月榜排序
    @Scheduled(cron = "0 0 0 1 * ? ")
    public void MonthRankScheduler() {
        rankService.monthRankScheduler();
    }

    // 每30分钟进行心跳检测
//    @Scheduled(cron = "0 */30 * * * ?")
//    public void heartCheck()
//    {
//        RestTemplate restTemplate = new RestTemplate();
//        try{
//            String info = restTemplate.getForObject("http://202.120.40.8:30609/api/test/all", String.class);
//            System.out.println(info);
//        }catch (Exception e)
//        {
//            mailService.sendSimpleMail("zydiii@sjtu.edu.cn", "【对抗仿真系统】 故障报警", "软件学院服务器断联，请及时检查修复! \n 报错内容如下：\n" + e.toString());
//        }
//
//        try{
//            String info = restTemplate.getForObject("http://10.119.7.179:30609/api/test/all", String.class);
//            System.out.println(info);
//        }catch (Exception e)
//        {
//            mailService.sendSimpleMail("zydiii@sjtu.edu.cn", "【对抗仿真系统】 故障报警", "网络中心服务器断联，请及时检查修复! \n 报错内容如下：\n" + e.toString());
//        }
//    }

//    private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private int fixedDelayCount = 1;
//    private int fixedRateCount = 1;
//    private int initialDelayCount = 1;
//    private int cronCount = 1;

//    @Scheduled(fixedDelay = 5000)        //fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
//    public void testFixDelay() {
//        logger.info("===fixedDelay: 第{}次执行方法", fixedDelayCount++);
//    }
//
//    @Scheduled(fixedRate = 5000)        //fixedRate = 5000表示当前方法开始执行5000ms后，Spring scheduling会再次调用该方法
//    public void testFixedRate() {
//        logger.info("===fixedRate: 第{}次执行方法", fixedRateCount++);
//    }
//
//    @Scheduled(initialDelay = 1000, fixedRate = 5000)   //initialDelay = 1000表示延迟1000ms执行第一次任务
//    public void testInitialDelay() {
//        logger.info("===initialDelay: 第{}次执行方法", initialDelayCount++);
//    }
//
//    @Scheduled(cron = "0 0/1 * * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
//    public void testCron() {
//        logger.info("===initialDelay: 第{}次执行方法", cronCount++);
//    }
}
