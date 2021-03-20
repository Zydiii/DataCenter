package zyd.datacenter.Entities.Weapon;

import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;

@Document(value = "Equipment")
@ApiModel(value="Equipment", description="装备数据，目前没有用")
public class Equipment {
    @Id
    private String id;

    private String equipName; // 装备名称

    private File equipFile; // 装备模型文件

    private String type; // 装备类型

    @Version
    private Long version;

    public Equipment(String equipName, File equipFile, String type) {
        this.equipName = equipName;
        this.equipFile = equipFile;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public File getEquipFile() {
        return equipFile;
    }

    public void setEquipFile(File equipFile) {
        this.equipFile = equipFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
