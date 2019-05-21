package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoTipoRepository extends JpaRepository<VehiculoTipo,Long> {
    VehiculoTipo findByTipoStartsWithIgnoreCase(String tipo);

}
