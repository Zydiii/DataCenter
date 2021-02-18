package zyd.datacenter.Entities.Room;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Camp")
public class Camp {
    @Id
    private String id;

    private String campName; // 阵营名称

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
