package es.uca.iw.RTC;

import java.util.Set;

public class Usuario {
    private int _iId;
    private String _sDni, _sNombre, _sApellido1, _sApellido2, _sTelefono;
    private Set<Reserva> _Reservas;

    //Getters
    public int get_Id() {
        return _iId;
    }

    public Set<Reserva> get_Reservas() {
        return _Reservas;
    }

    public String get_Apellido1() {
        return _sApellido1;
    }

    public String get_Apellido2() {
        return _sApellido2;
    }

    public String get_Dni() {
        return _sDni;
    }

    public String get_Nombre() {
        return _sNombre;
    }

    public String get_Telefono() {
        return _sTelefono;
    }

    //Setters
    public void set_Id(int Id) {
        this._iId = Id;
    }

    public void set_Reservas(Set<Reserva> Reservas) {
        this._Reservas = Reservas;
    }

    public void set_Apellido1(String Apellido1) {
        this._sApellido1 = Apellido1;
    }

    public void set_Apellido2(String Apellido2) {
        this._sApellido2 = Apellido2;
    }

    public void set_Dni(String Dni) {
        this._sDni = Dni;
    }

    public void set_Nombre(String Nombre) {
        this._sNombre = Nombre;
    }

    public void set_Telefono(String Telefono) {
        this._sTelefono = Telefono;
    }

}
