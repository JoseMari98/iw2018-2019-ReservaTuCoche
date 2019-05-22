package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoTipoRepository extends JpaRepository<VehiculoTipo,Long> {
    VehiculoTipo findByTipoStartsWithIgnoreCase(String tipo);
    List<VehiculoTipo> findByMarca(VehiculoMarca vehiculoMarca);
}
