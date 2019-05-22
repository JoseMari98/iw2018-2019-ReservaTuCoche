package es.uca.iw;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long codigo;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Vehiculo vehiculo;
    private LocalDate fechaInicio, fechaFin;
    private double precioTotal;
    @OneToMany(mappedBy = "reserva")
    private Set<Pago> pagosSet = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private ReservaEstado estado;

    //Getters
    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Pago> getPagosSet() {
        return pagosSet;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Long getId() {
        return id;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public Long getCodigo() {
        return codigo;
    }

    public ReservaEstado getEstado() {
        return estado;
    }

    //Setters
    public void setUsuario(Usuario Usuario) {
        this.usuario = Usuario;
    }

    public void setPagosSet(Set<Pago> pagosSet) {
        this.pagosSet = pagosSet;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void setFechaFin(LocalDate FechaFin) {
        this.fechaFin = FechaFin;
    }

    public void setFechaInicio(LocalDate FechaInicio) {
        this.fechaInicio = FechaInicio;
    }

    public void setVehiculo(Vehiculo Vehiculo) {
        this.vehiculo = Vehiculo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstado(ReservaEstado estado) {
        this.estado = estado;
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
