package fitnescenter.endpoit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fitnescenter.endpoit.entity.MenuLayanan;

import java.util.Optional;

@Repository
public interface MenuLayananRepository extends JpaRepository<MenuLayanan, String> {

    Optional<MenuLayanan> findFirstByNamaPaket(String namaPaket);
}
