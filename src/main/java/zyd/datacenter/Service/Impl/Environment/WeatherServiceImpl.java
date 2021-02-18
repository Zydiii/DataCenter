package zyd.datacenter.Service.Impl.Environment;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Environment.WeatherRepository;
import zyd.datacenter.Service.Environment.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {
    private WeatherRepository weatherRepository;

    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }
}
