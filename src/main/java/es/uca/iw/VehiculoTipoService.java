package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoTipoService {
    private VehiculoTipoRepository repoTipo;

    @Autowired
    private VehiculoTipoService(VehiculoTipoRepository repoTipo) {
        this.repoTipo = repoTipo;
    }

    public synchronized VehiculoTipo guardarTipo(VehiculoTipo entrada) {
        return repoTipo.save(entrada);
    }

    public Optional<VehiculoTipo> buscarIdTipo(Long id) {
        return repoTipo.findById(id);
    }

    public List<VehiculoTipo> listarTipo() {
        return repoTipo.findAll();
    }

    public Long contarTipo() {
        return repoTipo.count();
    }

    public void borrarTipo(VehiculoTipo entidad) {
        repoTipo.delete(entidad);
    }

    public VehiculoTipo listarTipoPorTipo(String tipo) {
        return repoTipo.findByTipoStartsWithIgnoreCase(tipo);
    }

    public List<VehiculoTipo> listarPorMarca(VehiculoMarca vehiculoMarca) {
        return repoTipo.findByMarca(vehiculoMarca);
    }
}
