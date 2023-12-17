package fitnescenter.endpoit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fitnescenter.endpoit.entity.OrderPaket;
import fitnescenter.endpoit.entity.User;

import java.util.Optional;

@Repository
public interface OrderPaketRepository extends JpaRepository<OrderPaket, String> {

    Optional<OrderPaket> findByUser(User user);

    Optional<OrderPaket> findFirstById(String idOrder);

    boolean existsByUser(User user);



}
