package zyd.datacenter.Entities.Asset;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;

@Document(value = "Asset")
public class Asset {
    @Id
    private String id;

    private String versionId; // 版本号

    private File assetFile; // 资源包文件

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
