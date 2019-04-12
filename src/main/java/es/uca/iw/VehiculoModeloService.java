package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoModeloService {
    private VehiculoModeloRepository repoModelo;
    @Autowired
    private VehiculoModeloService(VehiculoModeloRepository repoModelo) {
        this.repoModelo = repoModelo;
    }
    public synchronized VehiculoModelo guardarModelo(VehiculoModelo entrada) {
        return repoModelo.save(entrada);
    }

    public Optional<VehiculoModelo> buscarIdModelo(Long id) {
        return repoModelo.findById(id);
    }

    public List<VehiculoModelo> listarModelo() {
        return repoModelo.findAll();
    }

    public Long contarModelo() {
        return repoModelo.count();
    }

    public void borrarModelo(VehiculoModelo entidad) {
        repoModelo.delete(entidad);
    }
}
