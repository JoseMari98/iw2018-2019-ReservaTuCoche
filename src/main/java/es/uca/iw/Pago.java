package es.uca.iw;

import javax.persistence.*;

public class Pago {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Reserva reserva;
}
