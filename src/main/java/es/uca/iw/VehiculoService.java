package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    private VehiculoRepository repo;
    @Autowired
    private VehiculoService(VehiculoRepository repo) {
        this.repo = repo;
    }

    public synchronized Vehiculo save(Vehiculo entrada) {
        return repo.save(entrada);
    }

    public Optional<Vehiculo> findById(Long id) {
        return repo.findById(id);
    }

    public Iterable<Vehiculo> findAll() {
        return repo.findAll();
    }

    public Long count() {
        return repo.count();
    }

    public void delete (Vehiculo entidad) {
        repo.delete(entidad);
    }

}
