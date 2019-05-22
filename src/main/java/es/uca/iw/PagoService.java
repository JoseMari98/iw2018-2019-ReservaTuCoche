package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {
    PagoRepository repoPago;

    private PagoService(PagoRepository repoPago) {
        this.repoPago = repoPago;
    }

    public synchronized Pago guardarPago(Pago entrada) {
        return repoPago.save(entrada);
    }

    public Optional<Pago> buscarIdPago(Long id) {
        return repoPago.findById(id);
    }

    public List<Pago> listarPago() {
        return repoPago.findAll();
    }

    public Long contarPago() {
        return repoPago.count();
    }

    public void borrarPago(Pago entidad) {
        repoPago.delete(entidad);
    }

    public List<Pago> listarPorReserva(Reserva reserva){
        return repoPago.findByReserva(reserva);
    }
}
