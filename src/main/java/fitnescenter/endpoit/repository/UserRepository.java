package fitnescenter.endpoit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fitnescenter.endpoit.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    
}
