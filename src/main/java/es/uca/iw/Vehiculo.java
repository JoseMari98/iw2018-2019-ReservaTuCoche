package es.uca.iw;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Vehiculo implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "vehiculo")
    private Set<Reserva> reservaSet = new HashSet<>();
    private Double precio;
    @NotEmpty(message = "La matricula es obligatoria")
    private String matricula = "";
    @ManyToOne
    @NotNull(message = "La marca es obligatoria")
    private VehiculoMarca marca;
    @ManyToOne
    @NotNull(message = "El modelo es obligatorio")
    private VehiculoModelo modelo;

    //Getters
    public Long getId() {
        return id;
    }

    public Set<Reserva> getReservas() {
        return reservaSet;
    }

    public Double getPrecio() {
        return precio;
    }

    public VehiculoMarca getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public VehiculoModelo getModelo() {
        return modelo;
    }

    //Setters
    public void setId(Long Id) {
        this.id = Id;
    }

    public void setReservas(Set<Reserva> Reservas) {
        this.reservaSet = Reservas;
    }

    public void setPrecio(Double Precio) {
        this.precio = Precio;
    }

    public void setMarca(VehiculoMarca Marca) {
        this.marca = Marca;
    }

    public void setMatricula(String Matricula) {
        this.matricula = Matricula;
    }

    public void setModelo(VehiculoModelo Modelo) {
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