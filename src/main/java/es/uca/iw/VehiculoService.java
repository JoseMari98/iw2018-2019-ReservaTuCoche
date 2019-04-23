package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    private VehiculoRepository repoVehiculo;
    private VehiculoMarcaRepository repoMarca;

    @Autowired
    private VehiculoService(VehiculoRepository repoVehiculo, VehiculoModeloRepository repoModelo, VehiculoMarcaRepository repoMarca) {
        this.repoVehiculo = repoVehiculo;
        this.repoMarca = repoMarca;
    }

    public synchronized Vehiculo guardarVehiculo(Vehiculo entrada) {
        return repoVehiculo.save(entrada);
    }

    public Optional<Vehiculo> buscarIdVehiculo(Long id) {
        return repoVehiculo.findById(id);
    }

    public List<Vehiculo> listarVehiculo() {
        return repoVehiculo.findAll();
    }

    public Long contarVehiculo() {
        return repoVehiculo.count();
    }

    public void borrarVehiculo(Vehiculo entidad) {
        repoVehiculo.delete(entidad);
    }

    public List<Vehiculo> listarVehiculoPorMatricula(String matricula) {
        return repoVehiculo.findByMatriculaStartsWithIgnoreCase(matricula);
    }

    public List<Vehiculo> listarVehiculoPorMarca(String marca) {
        return repoVehiculo.findByMarca(repoMarca.findByMarcaStartsWithIgnoreCase(marca));
    }
}
