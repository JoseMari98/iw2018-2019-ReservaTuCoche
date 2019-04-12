package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    List<Vehiculo> findAllByMatricula(String matricula);
}