package zyd.datacenter.Repository.Weapon;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.Weapon.Equipment;

public interface EquipmentRepository extends MongoRepository<Equipment, String> {
}
