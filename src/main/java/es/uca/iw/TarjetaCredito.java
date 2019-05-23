package es.uca.iw;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class TarjetaCredito {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Rellene el campo")
    @Column(unique = true)
    private String numeroTarjeta = "";
    @NotEmpty(message = "Rellene el campo")
    private String numeroSeguridad = "";
    @NotNull(message = "Rellene el campo")
    private LocalDate fechaCaducidad;
    @ManyToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getNumeroSeguridad() {
        return numeroSeguridad;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void setNumeroSeguridad(String numeroSeguridad) {
        this.numeroSeguridad = numeroSeguridad;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
