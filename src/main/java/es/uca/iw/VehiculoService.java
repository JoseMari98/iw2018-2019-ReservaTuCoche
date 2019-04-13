package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    private VehiculoRepository repoVehiculo;

    @Autowired
    private VehiculoService(VehiculoRepository repoVehiculo, VehiculoModeloRepository repoModelo, VehiculoMarcaRepository repoMarca) {
        this.repoVehiculo = repoVehiculo;
    }

    public synchronized Vehiculo guardarVehiculo(Vehiculo entrada) {
        return repoVehiculo.save(entrada);
    }

    public Optional<Vehiculo> buscarIdVehiculo(Long id) {
        return repoVehiculo.findById(id);
    }

    public List<Vehiculo> listarVehiculo() {
        return this.listarVehiculo("");
    }

    public Long contarVehiculo() {
        return repoVehiculo.count();
    }

    public void borrarVehiculo(Vehiculo entidad) {
        repoVehiculo.delete(entidad);
    }

    public List<Vehiculo> listarVehiculo(String matricula) {
        return repoVehiculo.findAllByMatricula(matricula);
    }
}
