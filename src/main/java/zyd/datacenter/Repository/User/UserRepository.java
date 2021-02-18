package zyd.datacenter.Repository.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import zyd.datacenter.Entities.User.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S s);

    @Override
    <S extends User> S insert(S s);

    @Override
    Optional<User> findById(String s);
}
