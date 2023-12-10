package fitnescenter.endpoit.repository;

import fitnescenter.endpoit.entity.ServiceFitnes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.Optional;

@Repository
public interface ServiceFitnesRepository extends JpaRepository<ServiceFitnes, String> {

}
