package zyd.datacenter.Entities.Asset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;

@Document(value = "Asset")
@ApiModel(value="Asset", description="资源包数据，存储资源包数据，目前还没有实现")
public class Asset {
    @Id
    private String id;

    @ApiModelProperty(value = "最新版本号")
    private String versionId; // 版本号

    @ApiModelProperty(value = "资源包文件")
    private File assetFile; // 资源包文件

    @Version
    private Long version;

    public Asset(String versionId, File assetFile) {
        this.versionId = versionId;
        this.assetFile = assetFile;
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

    public File getAssetFile() {
        return assetFile;
    }

    public void setAssetFile(File assetFile) {
        this.assetFile = assetFile;
    }
}
