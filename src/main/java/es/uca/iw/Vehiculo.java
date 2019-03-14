package es.uca.iw;

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
    public void set_Id(int Id) {
        this._iId = Id;
    }

    public void set_Reservas(Set<Reserva> Reservas) {
        this._Reservas = Reservas;
    }

    public void set_Precio(int Precio) {
        this._iPrecio = Precio;
    }

    public void set_Marca(String Marca) {
        this._sMarca = Marca;
    }

    public void set_Matricula(String Matricula) {
        this._sMatricula = Matricula;
    }

    public void set_Modelo(String Modelo) {
        this._sModelo = Modelo;
    }
}