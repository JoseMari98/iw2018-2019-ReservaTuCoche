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
    private VehiculoService(VehiculoRepository repoVehiculo, VehiculoModeloRepository repoModelo, VehiculoMarcaRepository repoMarca) {
        this.repoVehiculo = repoVehiculo;
        this.repoMarca = repoMarca;
        this.repoModelo = repoModelo;
    }

    public synchronized Vehiculo guardarVehiculo(Vehiculo entrada) {
        return repoVehiculo.save(entrada);
    }

    public Optional<Vehiculo> buscarIdVehiculo(Long id) {
        return repoVehiculo.findById(id);
    }

    public Iterable<Vehiculo> listarVehiculo() {
        return repoVehiculo.findAll();
    }

    public Long contarVehiculo() {
        return repoVehiculo.count();
    }

    public void borrarVehiculo(Vehiculo entidad) {
        repoVehiculo.delete(entidad);
    }


    public synchronized VehiculoMarca guardarMarca(VehiculoMarca entrada) {
        return repoMarca.save(entrada);
    }

    public Optional<VehiculoMarca> buscarIdMarca(Long id) {
        return repoMarca.findById(id);
    }

    public Iterable<VehiculoMarca> listarMarca() {
        return repoMarca.findAll();
    }

    public Long contarMarca() {
        return repoMarca.count();
    }

    public void borrarMarca(VehiculoMarca entidad) {
        repoMarca.delete(entidad);
    }


    public synchronized VehiculoModelo guardarModelo(VehiculoModelo entrada) {
        return repoModelo.save(entrada);
    }

    public Optional<VehiculoModelo> buscarIdModelo(Long id) {
        return repoModelo.findById(id);
    }

    public Iterable<VehiculoModelo> listarModelo() {
        return repoModelo.findAll();
    }

    public Long contarModelo() {
        return repoModelo.count();
    }

    public void borrarModelo(VehiculoModelo entidad) {
        repoModelo.delete(entidad);
    }

}
