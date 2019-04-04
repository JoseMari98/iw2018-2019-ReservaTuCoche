package es.uca.iw;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario implements UserDetails {
    @Id //esto sirve para decir cual es el id
    @GeneratedValue(strategy= GenerationType.AUTO) //esto para que se genere aleatorio
    private int id;
    private String dni = "", nombre = "", apellido1 = "", apellido2 = "", telefono = "", email = "", contrasena = "";
    @OneToMany(mappedBy = "usuario") //esto para decir la cardinalidad y a que variable se asocia
    private Set<Reserva> reservaSet = new HashSet<>();

    //Getters
    public int getId() {
        return id;
    }

    public Set<Reserva> getReservas() {
        return reservaSet;
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

    public void setReservas(Set<Reserva> Reservas) {
        this.reservaSet = Reservas;
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
