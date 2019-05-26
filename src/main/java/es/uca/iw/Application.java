package es.uca.iw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EnableJpaRepositories
    public class Config {
    }

    @Bean
    public CommandLineRunner loadData(UsuarioService usuarioService, TarjetaCreditoService creditoService) {
        return (args) -> {
            try {
                boolean valido = usuarioService.loadUserByUsername("admin").getRole().equals("Admin");
            } catch (UsernameNotFoundException e) {
                Usuario u = new Usuario();
                u.setNombre("admin");
                u.setPassword("admin");
                u.setApellido1("admin");
                u.setApellido2("admin");
                u.setEmail("admin@admin.es");
                u.setUsername("admin");
                u.setRole("Admin");
                u.setTelefono("9");
                u.setDni("9");
                usuarioService.create(u);
                if(creditoService.listarPorNumero("1234567890123456") == null) {
                    TarjetaCredito tarjetaCredito = new TarjetaCredito();
                    tarjetaCredito.setNumeroTarjeta("1234567890123456");
                    tarjetaCredito.setFechaCaducidad(LocalDate.of(2024, 10, 3));
                    tarjetaCredito.setNumeroSeguridad("232");
                    tarjetaCredito.setUsuario(u);
                    creditoService.guardarTarjeta(tarjetaCredito);
                }
            }
            try {
                boolean valido = usuarioService.loadUserByUsername("gerente").getRole().equals("Gerente");
            } catch (UsernameNotFoundException e) {
                Usuario u = new Usuario();
                u.setNombre("gerente");
                u.setPassword("gerente");
                u.setApellido1("gerente");
                u.setApellido2("gerente");
                u.setEmail("gerente@gerente.es");
                u.setUsername("gerente");
                u.setRole("Gerente");
                u.setTelefono("9");
                u.setDni("9");
                usuarioService.create(u);
            }
        };
    }
    @Bean
    public CommandLineRunner LoadData(UsuarioService usuarioService) {
        return (args) -> {
            try {
                boolean valido = usuarioService.loadUserByUsername("gerente").getRole().equals("Gerente");
            } catch (UsernameNotFoundException e) {
                Usuario u = new Usuario();
                u.setNombre("gerente");
                u.setPassword("gerente");
                u.setApellido1("gerente");
                u.setApellido2("gerente");
                u.setEmail("gerente@gerente.es");
                u.setUsername("gerente");
                u.setRole("Gerente");
                u.setTelefono("9");
                u.setDni("9");
                usuarioService.create(u);

            }
        };
    }
}