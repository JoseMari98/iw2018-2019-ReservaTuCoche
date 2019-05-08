package es.uca.iw;

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

    @Bean(name = "Passwordencoder")
    public PasswordEncoder Encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @EnableJpaRepositories
    public class Config {
    }

    /*@Bean
    public void creador() {
        PagoService pagoService;
        Vehiculo vehiculo = new Vehiculo();
        Usuario u = new Usuario();
        Reserva r = new Reserva();
        vehiculo.setMatricula("culo");
        u.setNombre("ratilla");
        r.setUsuario(u);
        r.setVehiculo(vehiculo);
    }*/
}