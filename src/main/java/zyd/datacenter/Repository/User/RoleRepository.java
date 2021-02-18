package zyd.datacenter.Repository.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.User.ERole;
import zyd.datacenter.Entities.User.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
