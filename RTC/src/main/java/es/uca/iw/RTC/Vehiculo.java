package es.uca.iw.RTC;

import java.util.Set;

public class Vehiculo {
    private int _iId, _iPrecio;
    private String _sMatricula, _sMarca, _sModelo;
    private Set<Reserva> _Reservas;

    //Getters
    public int get_Id() {
        return _iId;
    }

    public Set<Reserva> get_Reservas() {
        return _Reservas;
    }

    public int get_Precio() {
        return _iPrecio;
    }

    public String get_Marca() {
        return _sMarca;
    }

    public String get_Matricula() {
        return _sMatricula;
    }

    public String get_Modelo() {
        return _sModelo;
    }

    //Setters
    public void set_Id(int iId) {
        this._iId = iId;
    }

    public void set_Reservas(Set<Reserva> Reservas) {
        this._Reservas = Reservas;
    }

    public void set_Precio(int iPrecio) {
        this._iPrecio = iPrecio;
    }

    public void set_Marca(String sMarca) {
        this._sMarca = sMarca;
    }

    public void set_Matricula(String sMatricula) {
        this._sMatricula = sMatricula;
    }

    public void set_Modelo(String sModelo) {
        this._sModelo = sModelo;
    }
}
