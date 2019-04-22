package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoMarcaRepository extends JpaRepository<VehiculoMarca, Long> {
    List<VehiculoMarca> findByMarcaStartsWithIgnoreCase(String marca);

}
