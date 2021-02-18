package zyd.datacenter.Service.Impl.User;

import org.springframework.stereotype.Service;
import zyd.datacenter.Repository.User.RoleRepository;
import zyd.datacenter.Service.User.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
