package zyd.datacenter.Service.Impl.Weapon;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.Weapon.WeaponRepository;
import zyd.datacenter.Service.Weapon.WeaponService;

@Service
public class WeaponServiceImpl implements WeaponService {
    private WeaponRepository weaponRepository;

    public WeaponServiceImpl(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }
}
