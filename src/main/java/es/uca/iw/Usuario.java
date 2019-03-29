package es.uca.iw;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Usuario {
    //@Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String dni = "", nombre = "", apellido1 = "", apellido2 = "", telefono = "", email = "", contrasena = "";
    private ArrayList<Reserva> reservas = new ArrayList<>();

    //Getters
    public int getId() {
        return id;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEmail() {
        return email;
    }

    //Setters
    public void setId(int Id) {
        this.id = Id;
    }

    public void setReservas(ArrayList<Reserva> Reservas) {
        this.reservas = Reservas;
    }

    public void setApellido1(String Apellido1) {
        this.apellido1 = Apellido1;
    }

    public void setApellido2(String Apellido2) {
        this.apellido2 = Apellido2;
    }

    public void setDni(String Dni) {
        this.dni = Dni;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public void setTelefono(String Telefono) {
        this.telefono = Telefono;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public void setContrasena(String Contrasena) {
        this.contrasena = Contrasena;
    }
}
