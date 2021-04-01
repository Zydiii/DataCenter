package zyd.datacenter.Entities.Asset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Document(value = "Asset")
@ApiModel(value="Asset", description="资源包数据，存储资源包数据，目前还没有实现")
public class Asset {
    @Id
    private String id;

    @ApiModelProperty(value = "最新版本号")
    private String versionId; // 版本号

    @ApiModelProperty(value = "资源包文件地址")
    private String dataUrl; // 资源包文件

    @ApiModelProperty(value = "发布者用户id")
    private String userId; // 发布者用户id

    @ApiModelProperty(value = "发布时间")
    private Date date; // 发布时间

    @ApiModelProperty(value = "发布的北京时间")
    private String bjDate; // 北京时间

    @ApiModelProperty(value = "发布的时间戳")
    @Indexed(direction = IndexDirection.DESCENDING)
    private long timeStamp; // 时间戳

    @Version
    private Long version;

    public Asset(String versionId, String dataUrl, String userId) {
        this.versionId = versionId;
        this.dataUrl = dataUrl;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        this.bjDate = bjSdf.format(date);
        this.timeStamp = date.getTime();
    }

    public String getBjDate() {
        return bjDate;
    }

    public void setBjDate(String bjDate) {
        this.bjDate = bjDate;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
