package fitnescenter.endpoit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import fitnescenter.endpoit.entity.CreditCard;
import fitnescenter.endpoit.entity.Users;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>, JpaSpecificationExecutor<CreditCard> {

    Optional<CreditCard> findFirstByUserId(Users users);

}
