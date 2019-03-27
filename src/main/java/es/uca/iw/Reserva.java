package es.uca.iw;

import java.util.Date;

public class Reserva {
    private Usuario usuario;
    private Vehiculo vehiculo;
    private Date fechaInicio, fechaFin;

    //Getters
    public Usuario getUsuario() {
        return usuario;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    //Setters
    public void setUsuario(Usuario Usuario) {
        this.usuario = Usuario;
    }

    public void setFechaFin(Date FechaFin) {
        this.fechaFin = FechaFin;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.fechaInicio = FechaInicio;
    }

    public void setVehiculo(Vehiculo Vehiculo) {
        this.vehiculo = Vehiculo;
    }

}
