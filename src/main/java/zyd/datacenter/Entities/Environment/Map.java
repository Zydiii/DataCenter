package zyd.datacenter.Entities.Environment;

import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;

@Document(value = "Map")
@ApiModel(value="Map", description="地图数据，存储地图数据，目前没有用")
public class Map {
    @Id
    private String id;

    private String mapName; // 地图名

    private File mapFile; // 地形地图文件

    @Version
    private Long version;

    public Map(String mapName, File mapFile) {
        this.mapName = mapName;
        this.mapFile = mapFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public File getMapFile() {
        return mapFile;
    }

    public void setMapFile(File mapFile) {
        this.mapFile = mapFile;
    }
}
