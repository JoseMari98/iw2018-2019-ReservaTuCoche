package es.uca.iw;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Reserva reserva;
    @NotEmpty(message = "Rellene el campo")
    private String tarjetaCredito = "", propietario = "", numeroSeguridad = "";
    private double precioTotal;
    @NotNull(message = "Rellene el campo")
    private LocalDate fechaCaducidad;

    public double getPrecioTotal() {
        return precioTotal;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public String getNumeroSeguridad() {
        return numeroSeguridad;
    }

    public String getPropietario() {
        return propietario;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void setNumeroSeguridad(String numeroSeguridad) {
        this.numeroSeguridad = numeroSeguridad;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }
}
