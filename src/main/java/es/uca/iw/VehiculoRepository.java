package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Vehiculo findByMatriculaStartsWithIgnoreCase(String matricula);
    List<Vehiculo> findByCiudad(@Param("ciudad") VehiculoCiudad ciudad);
    List<Vehiculo> findByMarcaAndCiudad(VehiculoMarca marca, VehiculoCiudad ciudad);
    List<Vehiculo> findByModeloAndCiudad(VehiculoModelo modelo, VehiculoCiudad ciudad);
    List<Vehiculo> findByMarcaAndModeloAndCiudad(VehiculoMarca marca, VehiculoModelo modelo, VehiculoCiudad ciudad);

    List<Vehiculo> findByMarca(VehiculoMarca marca);
    List<Vehiculo> findByModelo(VehiculoModelo modelo);
    List<Vehiculo> findByMarcaAndModelo(VehiculoMarca marca, VehiculoModelo modelo);
}