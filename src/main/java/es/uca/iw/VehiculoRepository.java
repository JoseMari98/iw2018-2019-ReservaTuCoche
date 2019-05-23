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
    @Query("select v from Vehiculo v, Reserva where ciudad = ?3 and (fecha_inicio not between ?1 and ?2) and (fecha_fin not between ?1 and ?2) and (?1 not between fecha_inicio and fecha_fin) and (?2 not between fecha_inicio and fecha_fin)")
    List<Vehiculo> findByFechaAndCiudad(LocalDate fechaInicio, LocalDate fechaFin, String ciudad);
    @Query("select v from Vehiculo v, Reserva where ciudad = ?3 and (fecha_inicio not between ?1 and ?2) and (fecha_fin not between ?1 and ?2) and (?1 not between fecha_inicio and fecha_fin) and (?2 not between fecha_inicio and fecha_fin) and marca_id = ?4")
    List<Vehiculo> findByFechaAndCiudadAndMarca(LocalDate fechaInicio, LocalDate fechaFin, String ciudad, Long marca);
    @Query("select v from Vehiculo v, Reserva where ciudad = ?3 and (fecha_inicio not between ?1 and ?2) and (fecha_fin not between ?1 and ?2) and (?1 not between fecha_inicio and fecha_fin) and (?2 not between fecha_inicio and fecha_fin) and modelo_id = ?4")
    List<Vehiculo> findByFechaAndCiudadAndModelo(LocalDate fechaInicio, LocalDate fechaFin, String ciudad, Long modelo);
    @Query("select v from Vehiculo v, Reserva where ciudad = ?3 and (fecha_inicio not between ?1 and ?2) and (fecha_fin not between ?1 and ?2) and (?1 not between fecha_inicio and fecha_fin) and (?2 not between fecha_inicio and fecha_fin) and marca_id = ?4 and modelo_id = ?5")
    List<Vehiculo> findByFechaAndCiudadAndMarcaAndModelo(LocalDate fechaInicio, LocalDate fechaFin, String ciudad, Long marca, Long modelo);
    List<Vehiculo> findByMarca(VehiculoMarca marca);
    List<Vehiculo> findByModelo(VehiculoModelo modelo);
    List<Vehiculo> findByMarcaAndModelo(VehiculoMarca marca, VehiculoModelo modelo);
}