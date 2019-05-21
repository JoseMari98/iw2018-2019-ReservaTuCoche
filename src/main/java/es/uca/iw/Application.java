package es.uca.iw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EnableJpaRepositories
    public class Config {
    }
    /*
    @Bean
    public CommandLineRunner loadData(ReservaRepository reservaRepository, VehiculoRepository vehiculoRepository, UsuarioRepository usuarioRepository, VehiculoMarcaRepository marcaRepository, VehiculoModeloRepository modeloRepository) {
        return (args) -> {
            VehiculoModelo vmod = new VehiculoModelo();
            VehiculoMarca vmar = new VehiculoMarca();
            Vehiculo v = new Vehiculo();
            Usuario u = new Usuario();
            Reserva r = new Reserva();

            vmod.setModelo("sport");
            vmar.setMarca("bmw");
            v.setMatricula("34");
            v.setMarca(vmar);
            v.setModelo(vmod);
            u.setNombre("pepe");
            r.setVehiculo(v);
            r.setUsuario(u);

            modeloRepository.save(vmod);
            marcaRepository.save(vmar);
            usuarioRepository.save(u);
            vehiculoRepository.save(v);
            reservaRepository.save(r);


        };
    }*/
}