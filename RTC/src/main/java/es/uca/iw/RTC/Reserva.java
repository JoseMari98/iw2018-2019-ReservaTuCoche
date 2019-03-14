package es.uca.iw.RTC;

import java.util.Date;

public class Reserva {
    private Cliente _Cliente;
    private Vehiculo _Vehiculo;
    private Date _FechaInicio, _FechaFin;

    //Getters
    public Cliente get_Cliente() {
        return _Cliente;
    }

    public Date get_FechaFin() {
        return _FechaFin;
    }

    public Date get_FechaInicio() {
        return _FechaInicio;
    }

    public Vehiculo get_Vehiculo() {
        return _Vehiculo;
    }

    //Setters
    public void set_Cliente(Cliente Cliente) {
        this._Cliente = Cliente;
    }

    public void set_FechaFin(Date FechaFin) {
        this._FechaFin = FechaFin;
    }

    public void set_FechaInicio(Date FechaInicio) {
        this._FechaInicio = FechaInicio;
    }

    public void set_Vehiculo(Vehiculo Vehiculo) {
        this._Vehiculo = Vehiculo;
    }

}
