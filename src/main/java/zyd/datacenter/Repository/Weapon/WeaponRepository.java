package zyd.datacenter.Repository.Weapon;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Weapon.Weapon;

public interface WeaponRepository extends MongoRepository<Weapon, String> {
}
