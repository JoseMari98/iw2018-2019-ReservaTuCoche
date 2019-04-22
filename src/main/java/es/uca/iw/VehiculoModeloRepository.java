package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoModeloRepository extends JpaRepository<VehiculoModelo, Long> {
    List<VehiculoModelo> findByModeloStartsWithIgnoreCase(String modelo);
}
