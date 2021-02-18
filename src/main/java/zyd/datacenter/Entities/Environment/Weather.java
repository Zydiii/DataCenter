package zyd.datacenter.Entities.Environment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Weather")
public class Weather {
    @Id
    private String id;

    private String weatherName; // 天气名称

    private String light; // 光照

    public Weather(String weatherName, String light) {
        this.weatherName = weatherName;
        this.light = light;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }
}
