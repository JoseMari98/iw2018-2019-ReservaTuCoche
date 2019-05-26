package es.uca.iw;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    public List<Vehiculo> listarVehiculo() {
        return repoVehiculo.findAll();
    }

    public Long contarVehiculo() {
        return repoVehiculo.count();
    }

    public void borrarVehiculo(Vehiculo entidad) {
        repoVehiculo.delete(entidad);
    }

    public Vehiculo listarVehiculoPorMatricula(String matricula) {
        return repoVehiculo.findByMatriculaStartsWithIgnoreCase(matricula);
    }

    public List<Vehiculo> listarVehiculoPorMarca(String marca) {
        return repoVehiculo.findByMarca(repoMarca.findByMarcaStartsWithIgnoreCase(marca));
    }

    public List<Vehiculo> listarVehiculoPorModelo(String modelo) {
        return repoVehiculo.findByModelo(repoModelo.findByModeloStartsWithIgnoreCase(modelo));
    }

    public List<Vehiculo> listarVehiculoPorMarcaYModelo(String marca, String modelo) {
        return repoVehiculo.findByMarcaAndModelo(repoMarca.findByMarcaStartsWithIgnoreCase(marca), repoModelo.findByModeloStartsWithIgnoreCase(modelo));
    }

    public List<Vehiculo> buscarPorMarcaYCiudad(String marca, VehiculoCiudad ciudad) {
        return repoVehiculo.findByMarcaAndCiudad(repoMarca.findByMarcaStartsWithIgnoreCase(marca), ciudad);
    }

    public List<Vehiculo> buscarPorModeloYCiudad(String modelo, VehiculoCiudad ciudad) {
        return repoVehiculo.findByModeloAndCiudad(repoModelo.findByModeloStartsWithIgnoreCase(modelo), ciudad);
    }

    public List<Vehiculo> buscarPorPrecioYCiudad(Double precio, VehiculoCiudad ciudad) {
        return repoVehiculo.findByPrecioAndCiudad(precio, ciudad);
    }

    public List<Vehiculo> buscarPorMarcaYPrecioYCiudad(String marca, Double precio, VehiculoCiudad ciudad) {
        return repoVehiculo.findByPrecioAndMarcaAndCiudad(precio, repoMarca.findByMarcaStartsWithIgnoreCase(marca), ciudad);
    }

    public List<Vehiculo> buscarPorModeloYPrecioYCiudad(String modelo, Double precio, VehiculoCiudad ciudad) {
        return repoVehiculo.findByPrecioAndModeloAndCiudad(precio, repoModelo.findByModeloStartsWithIgnoreCase(modelo), ciudad);
    }

    public List<Vehiculo> buscarPorMarcaYModeloYPrecioCiudad(String marca, String modelo, Double precio, VehiculoCiudad ciudad) {
        return repoVehiculo.findByPrecioAndMarcaAndModeloAndCiudad(precio, repoMarca.findByMarcaStartsWithIgnoreCase(marca), repoModelo.findByModeloStartsWithIgnoreCase(modelo), ciudad);
    }

    public List<Vehiculo> buscarPorMarcaYModeloYCiudad(String marca, String modelo, VehiculoCiudad ciudad) {
        return repoVehiculo.findByMarcaAndModeloAndCiudad(repoMarca.findByMarcaStartsWithIgnoreCase(marca), repoModelo.findByModeloStartsWithIgnoreCase(modelo), ciudad);
    }

    public List<Vehiculo> buscarPorCiudad(VehiculoCiudad ciudad) {
        return repoVehiculo.findByCiudad(ciudad);
    }

    public List<Vehiculo> listarPorTipo(VehiculoTipo vehiculoTipo) { return repoVehiculo.findByTipo(vehiculoTipo); }
}
