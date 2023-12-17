package fitnescenter.endpoit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fitnescenter.endpoit.entity.DetailLayanan;

import java.util.Optional;

@Repository
public interface DetailLayananRepository extends JpaRepository<DetailLayanan, String> {

    Optional<DetailLayanan> findFirstByNamaLatihan(String namaLatihan);

}
