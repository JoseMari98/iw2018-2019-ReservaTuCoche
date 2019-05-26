package es.uca.iw;

public class DevolverFianza {
    public static void devolver(PagoService pagoService, Reserva reserva) {
        Pago pago = new Pago();
        for(Pago p : pagoService.listarPorReserva(reserva)) {
            if(p.getTipo() == PagoTipo.Fianza) {
                TarjetaCredito destino = p.getOrigen();
                pago = p;
                pago.setOrigen(p.getDestino());
                pago.setDestino(destino);
            }
        }
        pagoService.guardarPago(pago);
    }
}
