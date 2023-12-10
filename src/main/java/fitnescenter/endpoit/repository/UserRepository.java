package fitnescenter.endpoit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fitnescenter.endpoit.entity.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findFirstByToken(String token);
}
