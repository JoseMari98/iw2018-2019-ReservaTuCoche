package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private ReservaRepository repo;
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ReservaService(ReservaRepository repo) {
        this.repo = repo;
    }

    public synchronized Reserva save(Reserva data) {
        return repo.save(data);
    }

    public Optional<Reserva> findById(Long id) {
        return repo.findById(id);
    }

    public List<Reserva> findAll() {
        return repo.findAll();
    }

    public Long count() {
        return repo.count();
    }

    public void delete (Reserva data) {
        repo.delete(data);
    }

    public List<Reserva> listarReservaPorMatricula(String matricula) {
        return repo.findByVehiculo(vehiculoRepository.findByMatriculaStartsWithIgnoreCase(matricula));
    }

    public List<Reserva> listarReservaPorVehiculo(Vehiculo v) {
        return repo.findByVehiculo(v);
    }

    public List<Reserva> listarPorUsuario(Usuario usuario) {
        return repo.findByUsuario(usuario);
    }

    public Reserva listarPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
}