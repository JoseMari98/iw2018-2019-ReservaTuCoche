package es.uca.iw;

import com.vaadin.flow.component.polymertemplate.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;

@Entity
public class Vehiculo implements Serializable, Cloneable {
    //@Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private double precio;
    private String matricula = "", marca = "", modelo = "";
    private ArrayList<Reserva> reservas;

    //Getters
    public Long getId() {
        return id;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public double getPrecio() {
        return precio;
    }

    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    //Setters
    public void setId(Long Id) {
        this.id = Id;
    }

    public void setReservas(ArrayList<Reserva> Reservas) {
        this.reservas = Reservas;
    }

    public void setPrecio(int Precio) {
        this.precio = Precio;
    }

    public void setMarca(String Marca) {
        this.marca = Marca;
    }

    public void setMatricula(String Matricula) {
        this.matricula = Matricula;
    }

    public void setModelo(String Modelo) {
        this.modelo = Modelo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Vehiculo && obj.getClass().equals(getClass())) {
            return Objects.equals(this.id, ((Vehiculo) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public Vehiculo clone() throws CloneNotSupportedException {
        return (Vehiculo) super.clone();
    }

    @Override
    public String toString() {
        return marca + " " + modelo;
    }
}