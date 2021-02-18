package zyd.datacenter.Entities.Environment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(value = "Environment")
public class Environment {
    @Id
    private String id;

    private String envName; // 环境名

    private String weatherId; // 天气编号

    private String season; // 季节

    private Date date; // 时间

    private String mapId; // 地图编号

    public Environment(String envName) {
        this.envName = envName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }
}
