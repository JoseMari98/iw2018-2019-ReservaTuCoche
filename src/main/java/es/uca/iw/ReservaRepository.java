package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByVehiculo(Vehiculo vehiculo);
    Long countByVehiculo(Vehiculo v);
    List<Reserva> findByUsuario(Usuario usuario);
    Reserva findByCodigo(Long codigo);
}