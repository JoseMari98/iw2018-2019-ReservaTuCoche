package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByVehiculo(Vehiculo vehiculo);
}