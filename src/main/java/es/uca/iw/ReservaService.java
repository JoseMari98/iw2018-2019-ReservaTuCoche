package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReservaService {
    private ReservaRepository repo;
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

    public Iterable<Reserva> findAll() {
        return repo.findAll();
    }

    public Long count() {
        return repo.count();
    }

    public void delete (Reserva data) {
        repo.delete(data);
    }

}