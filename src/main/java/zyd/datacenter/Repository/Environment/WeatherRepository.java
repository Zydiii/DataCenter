package zyd.datacenter.Repository.Environment;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherRepository extends MongoRepository<WeatherRepository, String> {
}
