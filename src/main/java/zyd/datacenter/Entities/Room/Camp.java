package zyd.datacenter.Entities.Room;

import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Camp")
@ApiModel(value="Camp", description="阵营数据，目前没有用")
public class Camp {
    @Id
    private String id;

    private String campName; // 阵营名称

    @Version
    private Long version;

    public Camp(String campName) {
        this.campName = campName;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }
}
