package es.uca.iw;

import javax.persistence.*;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Reserva reserva;
    private double cantidad;
    @ManyToOne
    private TarjetaCredito origen;
    @ManyToOne
    private TarjetaCredito destino;
    @Enumerated(EnumType.STRING)
    private PagoTipo tipo;

    public double getCantidad() {
        return cantidad;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Long getId() {
        return id;
    }

    public PagoTipo getTipo() {
        return tipo;
    }

    public TarjetaCredito getDestino() {
        return destino;
    }

    public TarjetaCredito getOrigen() {
        return origen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDestino(TarjetaCredito destino) {
        this.destino = destino;
    }

    public void setOrigen(TarjetaCredito origen) {
        this.origen = origen;
    }

    public void setTipo(PagoTipo tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

}
