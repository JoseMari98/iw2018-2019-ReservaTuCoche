package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoMarcaService {
    private VehiculoMarcaRepository repoMarca;

    @Autowired
    private VehiculoMarcaService(VehiculoMarcaRepository repoMarca) {
        this.repoMarca = repoMarca;
    }

    public synchronized VehiculoMarca guardarMarca(VehiculoMarca entrada) {
        return repoMarca.save(entrada);
    }

    public Optional<VehiculoMarca> buscarIdMarca(Long id) {
        return repoMarca.findById(id);
    }

    public List<VehiculoMarca> listarMarca() {
        return repoMarca.findAll();
    }

    public Long contarMarca() {
        return repoMarca.count();
    }

    public void borrarMarca(VehiculoMarca entidad) {
        repoMarca.delete(entidad);
    }

    public VehiculoMarca listarMarcaPorMarca(String marca) {
        return repoMarca.findByMarcaStartsWithIgnoreCase(marca);
    }
}
