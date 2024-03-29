package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository <Pago, Long>{
    List<Pago> findByReserva(Reserva reserva);
}
