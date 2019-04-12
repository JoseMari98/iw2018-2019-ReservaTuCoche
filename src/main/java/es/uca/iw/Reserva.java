package es.uca.iw;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Vehiculo vehiculo;
    private Date fechaInicio, fechaFin;

    //Getters
    public Usuario getUsuario() {
        return usuario;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Long getId() {
        return id;
    }

    //Setters
    public void setUsuario(Usuario Usuario) {
        this.usuario = Usuario;
    }

    public void setFechaFin(Date FechaFin) {
        this.fechaFin = FechaFin;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.fechaInicio = FechaInicio;
    }

    public void setVehiculo(Vehiculo Vehiculo) {
        this.vehiculo = Vehiculo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
