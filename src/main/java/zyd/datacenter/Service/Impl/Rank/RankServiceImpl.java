package zyd.datacenter.Service.Impl.Rank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Entities.Rank.*;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Entities.User.UserScore;
import zyd.datacenter.Repository.Rank.*;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Repository.User.UserScoreRepository;
import zyd.datacenter.Service.Rank.RankService;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class RankServiceImpl implements RankService {
    private UserRepository userRepository;

    private UserScoreRepository userScoreRepository;

    private RanksRepository ranksRepository;

    public RankServiceImpl(UserRepository userRepository, UserScoreRepository userScoreRepository, RanksRepository ranksRepository) {
        this.userRepository = userRepository;
        this.userScoreRepository = userScoreRepository;
        this.ranksRepository = ranksRepository;
    }

    public void allRankScheduler(){
//        Timer timer = new Timer();
//        TimerTask AllRankTask = new TimerTask(){
//            @Override
//            public void run() {
//                System.out.println("Start All Rank");
//                Rank(RankType.RANK_ALL);
//                System.out.println("Finish All Rank");
//            }
//        };
//        timer.schedule(AllRankTask, 0, 60000);
        System.out.println("Start All Rank");
        Rank(RankType.RANK_ALL);
        System.out.println("Finish All Rank");
    }

    public void dayRankScheduler()
    {
        System.out.println("Start Day Rank");
        Rank(RankType.RANK_DAY);
        System.out.println("Finish Day Rank");
    }

    public void weekRankScheduler(){
//        Calendar with = Calendar.getInstance();
//        Map<Integer, Integer> dayToDelay = new HashMap<Integer, Integer>();
//        dayToDelay.put(Calendar.FRIDAY, 1);
//        dayToDelay.put(Calendar.SATURDAY, 0);
//        dayToDelay.put(Calendar.SUNDAY, 6);
//        dayToDelay.put(Calendar.MONDAY, 5);
//        dayToDelay.put(Calendar.TUESDAY, 4);
//        dayToDelay.put(Calendar.WEDNESDAY, 3);
//        dayToDelay.put(Calendar.THURSDAY, 2);
//        int dayOfWeek = with.get(Calendar.DAY_OF_WEEK);
//        int hour = with.get(Calendar.HOUR_OF_DAY);
//        int delayInDays = dayToDelay.get(dayOfWeek);
//        int delayInHours = 0;
//        if(delayInDays == 6 && hour<14){
//            delayInHours = 14 - hour;
//        }else{
//            delayInHours = delayInDays*24+((24-hour)+14);
//        }
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(new WeekRankTask(), delayInHours,
//                179, TimeUnit.HOURS);
        System.out.println("Start Week Rank");
        Rank(RankType.RANK_WEEK);
        System.out.println("Finish Week Rank");
    }

    public void monthRankScheduler()
    {
        System.out.println("Start Month Rank");
        Rank(RankType.RANK_MONTH);
        System.out.println("Finish Month Rank");
    }

    public List<?> getRank(RankType rankType){
        return ranksRepository.findByRankType(rankType).getUserScoreList();
    }

    //@Transactional //(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void Rank(RankType rankType){
        HashMap<String, Float> getScoreMap;
        Date date = new Date();
        Ranks ranks = ranksRepository.findByRankType(rankType);
        List<UserScore> userScoreList = ranks.getUserScoreList();
        userScoreList.clear();

        switch (rankType)
        {
            case RANK_ALL: // 总榜直接算总分数即可
                List<User> users = userRepository.findAll();
                users.sort(comparatorUserScore);
                users.forEach(user -> {
                    UserScore userScore = new UserScore(user.getUsername(), user.getScore());
                    userScoreList.add(userScore);
                });
                break;
            case RANK_MONTH: // 月榜需要统计上一个月各个用户获取的积分
                long thisMonthTimestamp = getThisMonthFirstDay(date);
                long lastMonthTimestamp = getLastMonthFirstDay(date);
                // 统计特定时间内的分数
                getScoreMap = getScoreMapByTime(thisMonthTimestamp, lastMonthTimestamp);
                // 排序周榜并写入数据库
                for (Map.Entry<String, Float> entry : getScoreMap.entrySet()) {
                    UserScore userScore = new UserScore(entry.getKey(), entry.getValue());
                    userScoreList.add(userScore);
                }
                userScoreList.sort(comparatorScore);
                break;
            case RANK_WEEK: // 周榜需要统计上一周各个用户获取的积分
                long thisWeekTimestamp = getThisWeekMonday(date);
                long lastWeekTimestamp = getLastWeekMonday(date);
                // 统计特定时间内的分数
                getScoreMap = getScoreMapByTime(lastWeekTimestamp, thisWeekTimestamp);
                for (Map.Entry<String, Float> entry : getScoreMap.entrySet()) {
                    UserScore userScore = new UserScore(entry.getKey(), entry.getValue());
                    userScoreList.add(userScore);
                }
                userScoreList.sort(comparatorScore);
                break;
            case RANK_DAY: // 日榜需要统计上一天各个用户获取的积分
                //计算当天零点时间戳
                long nowTime = System.currentTimeMillis();
                long dailyStartTime = getDayStartTime(nowTime);
                long lastDailyStartTime = dailyStartTime - 86400000;
                // 统计特定时间内的分数
                getScoreMap = getScoreMapByTime(lastDailyStartTime, dailyStartTime);
                // 排序日榜并写入数据库
                for (Map.Entry<String, Float> entry : getScoreMap.entrySet()) {
                    UserScore userScore = new UserScore(entry.getKey(), entry.getValue());
                    userScoreList.add(userScore);
                }
                userScoreList.sort(comparatorScore);
                break;
            default:
                return;
        }

        ranks.setUserScoreList(userScoreList);
        ranksRepository.save(ranks);
    }

    // 统计特定时间内的分数
    HashMap<String, Float> getScoreMapByTime(long startTime, long endTime)
    {
        List<UserScore> userScores = userScoreRepository.getAllByTimestampBetween(startTime, endTime);
        HashMap<String, Float> getScoreMap = new HashMap<String, Float>();
        for (int i = 0; i < userScores.size(); i++)
        {
            String username = userScores.get(i).getUsername();
            if(getScoreMap.containsKey(username))
                getScoreMap.put(username, getScoreMap.get(username) + userScores.get(i).getScore());
            else
                getScoreMap.put(username, userScores.get(i).getScore());
        }
        return getScoreMap;
    }

    // 获取当日零点
    public long getDayStartTime(long nowTime)
    {
        return nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
    }

    // 获取当前月第一天
    public long getThisMonthFirstDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return getDayStartTime(c.getTimeInMillis());
    }

    // 获取当前月最后一天
    public long getThisMonthLastDay(Date date){
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDayStartTime(ca.getTimeInMillis());
    }

    // 获取上一月第一天
    public long getLastMonthFirstDay(Date date){
        //获取前月的第一天
        Calendar cal_1=Calendar.getInstance();//获取当前日期
        cal_1.setTime(date);
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return getDayStartTime(cal_1.getTimeInMillis());
    }

    // 获取上一月最后一天
    public long getLastMonthLastDay(Date date){
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.DAY_OF_MONTH,0);
        return getDayStartTime(cale.getTimeInMillis());
    }

    // 获取下个月第一天
    public long getNextMonthFirstDay(Date date){
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        //获取下个月第一天：
        cd.add(Calendar.MONTH, 1);
        //设置为1号,当前日期既为次月第一天
        cd.set(Calendar.DAY_OF_MONTH,1);
        return getDayStartTime(cd.getTimeInMillis());
    }

    // 获取本周第一天
    public long getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return getDayStartTime(cal.getTimeInMillis());
    }

    // 获取上一周第一天
    public long getLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return getDayStartTime(cal.getTimeInMillis());
    }

    // 获取下一周第一天
    public long getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return getDayStartTime(cal.getTimeInMillis());
    }


    // 分数比较器
    Comparator<UserScore> comparatorScore = new Comparator <UserScore>(){
        public int compare(UserScore user1,UserScore user2){
            if (user1.getScore() < user2.getScore())
                return 1;
            else if (user1.getScore() > user2.getScore())
                return -1;
            else
                return 0;
        }
    };

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

    Comparator<DayRank> comparatorUserDayScore = new Comparator <DayRank>(){
        public int compare(DayRank user1,DayRank user2){
            if (user1.getScore() < user2.getScore())
                return 1;
            else if (user1.getScore() > user2.getScore())
                return -1;
            else
                return 0;
        }
    };

    Comparator<WeekRank> comparatorUserWeekScore = new Comparator <WeekRank>(){
        public int compare(WeekRank user1,WeekRank user2){
            if (user1.getScore() < user2.getScore())
                return 1;
            else if (user1.getScore() > user2.getScore())
                return -1;
            else
                return 0;
        }
    };

    Comparator<MonthRank> comparatorUserMonthScore = new Comparator <MonthRank>(){
        public int compare(MonthRank user1,MonthRank user2){
            if (user1.getScore() < user2.getScore())
                return 1;
            else if (user1.getScore() > user2.getScore())
                return -1;
            else
                return 0;
        }
    };


    public void test(){
//        rankRepository.insert(new Rank("test", 1));
//        rankRepository.insert(new Rank("test1", 2));

//        try {
//            Rank rank = rankRepository.findById("60542add8bb71d7e8b696b15").get();
//            rank.setUsername("aaa");
//            rankRepository.save(rank);
//            rank = rankRepository.findById("60542add8bb71d7e8b696b16").get();
//            rank.setUsername("bbb");
//            rankRepository.save(rank);
//        }catch (Exception e)
//        {
//            System.out.println(e.toString());
//        }

    }
}

//class AllRankTask extends TimerTask {
//    @Autowired
//    RankService rankService;
//
//    @Override
//    public void run() {
//        System.out.println("start of all rank");
//        rankService.Rank(RankType.RANK_ALL);
//        System.out.println("end of all rank");
//    }
//}

class DayRankTask implements Runnable{
    @Autowired
    RankService rankService;

    @Override
    public void run() {
        System.out.println("start of day rank");
        rankService.Rank(RankType.RANK_DAY);
        System.out.println("end of day rank");
    }
}

class WeekRankTask implements Runnable{
    @Autowired
    RankService rankService;

    @Override
    public void run() {
        System.out.println("start of week rank");
        rankService.Rank(RankType.RANK_WEEK);
        System.out.println("end of week rank");
    }
}

class MonthRankTask implements Runnable{
    @Autowired
    RankService rankService;

    @Override
    public void run() {
        System.out.println("start of month rank");
        rankService.Rank(RankType.RANK_MONTH);
        System.out.println("end of month rank");
    }
}
