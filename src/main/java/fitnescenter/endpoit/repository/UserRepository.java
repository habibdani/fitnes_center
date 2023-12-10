package fitnescenter.endpoit.repository;

// import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fitnescenter.endpoit.entity.Users;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    // check email
    @Query(value = """
        SELECT
        * 
        FROM
            users 
        WHERE
            email LIKE :email 
            AND email IS NULL 
            AND deleted_at IS NULL
    """, nativeQuery = true)
    List<Map<String, Object>> checkEmail(String email);
    
}
