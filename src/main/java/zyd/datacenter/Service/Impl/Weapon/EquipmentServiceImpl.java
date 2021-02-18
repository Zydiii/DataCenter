package zyd.datacenter.Service.Impl.Weapon;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Weapon.EquipmentRepository;
import zyd.datacenter.Service.Weapon.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
}
