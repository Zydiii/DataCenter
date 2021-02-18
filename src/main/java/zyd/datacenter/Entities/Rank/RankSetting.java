package zyd.datacenter.Entities.Rank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Document(value = "RankSetting")
public class RankSetting {
    @Id
    private String id;

    private String userId; // 规则设定人编号

    private Date beginTime; // 排名开始时间

    private String bjBeginTime; // 开始北京时间

    private long beginTimeStamp; // 开始时间戳

    private Date endTime; // 排名结束时间

    private String bjEndTime; // 开始北京时间

    private long endTimeStamp; // 开始时间戳

    private long frequency; // 更新频率

    public RankSetting(String userId, Date beginTime, Date endTime, String bjEndTime, long endTimeStamp, long frequency) {
        this.userId = userId;
        this.beginTime = beginTime;
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        this.bjBeginTime = bjSdf.format(beginTime);
        this.beginTimeStamp = beginTime.getTime();
        this.endTime = endTime;
        this.bjEndTime = bjSdf.format(endTime);
        this.endTimeStamp = endTime.getTime();
        this.frequency = frequency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        this.bjBeginTime = bjSdf.format(beginTime);
        this.beginTimeStamp = beginTime.getTime();
    }

    public String getBjBeginTime() {
        return bjBeginTime;
    }

    public void setBjBeginTime(String bjBeginTime) {
        this.bjBeginTime = bjBeginTime;
    }

    public long getBeginTimeStamp() {
        return beginTimeStamp;
    }

    public void setBeginTimeStamp(long beginTimeStamp) {
        this.beginTimeStamp = beginTimeStamp;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        this.bjEndTime = bjSdf.format(endTime);
        this.endTimeStamp = endTime.getTime();
    }

    public String getBjEndTime() {
        return bjEndTime;
    }

    public void setBjEndTime(String bjEndTime) {
        this.bjEndTime = bjEndTime;
    }

    public long getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(long endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }
}
