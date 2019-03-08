package es.uca.iw.RTC;

import java.util.Set;

public class Cliente {
    private int _iId;
    private String _sDni, _sNombre, _sApellido1, _sApellido2, _sTelefono;
    private Set<Reserva> _Reservas;

    //Getters
    public int get_iId() {
        return _iId;
    }

    public Set<Reserva> get_Reservas() {
        return _Reservas;
    }

    public String get_sApellido1() {
        return _sApellido1;
    }

    public String get_sApellido2() {
        return _sApellido2;
    }

    public String get_sDni() {
        return _sDni;
    }

    public String get_sNombre() {
        return _sNombre;
    }

    public String get_sTelefono() {
        return _sTelefono;
    }

    //Setters
    public void set_iId(int iId) {
        this._iId = iId;
    }

    public void set_Reservas(Set<Reserva> Reservas) {
        this._Reservas = Reservas;
    }

    public void set_sApellido1(String sApellido1) {
        this._sApellido1 = sApellido1;
    }

    public void set_sApellido2(String sApellido2) {
        this._sApellido2 = sApellido2;
    }

    public void set_sDni(String sDni) {
        this._sDni = sDni;
    }

    public void set_sNombre(String sNombre) {
        this._sNombre = sNombre;
    }

    public void set_sTelefono(String sTelefono) {
        this._sTelefono = sTelefono;
    }

}
