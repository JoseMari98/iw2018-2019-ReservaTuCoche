package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VehiculoService {
    private VehiculoRepository repoVehiculo;
    private VehiculoMarcaRepository repoMarca;
    private VehiculoModeloRepository repoModelo;
    @Autowired
    private VehiculoService(VehiculoRepository repoVehiculo) {
        this.repoVehiculo = repoVehiculo;
    }

    public synchronized Vehiculo save(Vehiculo entrada) {
        return repoVehiculo.save(entrada);
    }

    public Optional<Vehiculo> findById(Long id) {
        return repoVehiculo.findById(id);
    }

    public Iterable<Vehiculo> findAll() {
        return repoVehiculo.findAll();
    }

    public Long count() {
        return repoVehiculo.count();
    }

    public void delete (Vehiculo entidad) {
        repoVehiculo.delete(entidad);
    }

}
