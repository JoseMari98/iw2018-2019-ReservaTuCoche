package es.uca.iw;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Campos obligatorios")
    @Min(value = 3, message = "El numero de puertas no puede ser menor que 3")
    @Max(value = 5, message = "El numero de puertas no puede ser mayor que 5")
    private Double puertas;
    @NotNull(message = "Campos obligatorios")
    @Min(value = 3, message = "El numero de plazas no puede ser menor que 3")
    @Max(value = 7, message = "El numero de plazas no puede ser mayor que 7")
    private Double plazas;
    @NotNull(message = "Campos obligatorios")
    @Enumerated(EnumType.STRING)
    private VehiculoAC ac;
    @NotNull(message = "Campos obligatorios")
    private Double precio;
    @NotEmpty(message = "La matricula es obligatoria")
    @Column(unique = true)
    private String matricula = "";
    @NotNull(message = "Campos obligatorios")
    @Enumerated(EnumType.STRING)
    private VehiculoMotor motor;
    @ManyToOne
    @NotNull(message = "Campos obligatorios")
    private VehiculoMarca marca;
    @ManyToOne
    @NotNull(message = "Campos obligatorios")
    private VehiculoModelo modelo;
    @ManyToOne
    @NotNull(message = "Campo obligatorio")
    private VehiculoTipo tipo;
    @NotNull(message = "Campo obligatorio")
    @Enumerated(EnumType.STRING)
    private VehiculoCiudad ciudad;

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

    public VehiculoCiudad getCiudad() {
        return ciudad;
    }

    public String getMatricula() {
        return matricula;
    }

    public VehiculoModelo getModelo() {
        return modelo;
    }

    public VehiculoTipo getTipo() {
        return tipo;
    }

    public VehiculoAC getAc() {
        return ac;
    }

    public Double getPuertas() {
        return puertas;
    }

    public VehiculoMotor getMotor() {
        return motor;
    }

    public Double getPlazas() {
        return plazas;
    }

    //Setters
    public void setId(Long Id) {
        this.id = Id;
    }

    public void setReservas(Set<Reserva> Reservas) {
        this.reservaSet = Reservas;
    }

    public void setCiudad(VehiculoCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setAc(VehiculoAC ac) {
        this.ac = ac;
    }

    public void setMotor(VehiculoMotor motor) {
        this.motor = motor;
    }

    public void setPlazas(Double plazas) {
        this.plazas = plazas;
    }

    public void setPuertas(Double puertas) {
        this.puertas = puertas;
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

    public void setTipo(VehiculoTipo tipo) {
        this.tipo = tipo;
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
        return marca.getMarca() + " " + modelo.getModelo();
    }
}