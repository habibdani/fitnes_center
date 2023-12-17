package fitnescenter.endpoit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fitnescenter.endpoit.entity.TambahanPertemuan;

import java.util.Optional;

@Repository
public interface TambahanPertemuanRepository extends JpaRepository<TambahanPertemuan, Integer> {

    boolean existsByIdOrderAndTambahanLatihan(String idOrder, String namaLatihan);

    Optional<TambahanPertemuan> findFirstByIdOrderAndId(String idOrder, Integer idTambahanPertemuan);

}
