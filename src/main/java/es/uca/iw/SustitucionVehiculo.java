package es.uca.iw;

import com.vaadin.flow.component.UI;

import java.util.List;

public class SustitucionVehiculo {
    public static void sustituir(ReservaService reservaService, Vehiculo vehiculo, VehiculoService vehiculoService, PagoService pagoService) {
        List<Reserva> reservaList = reservaService.listarReservaPorVehiculo(vehiculo);
        List<Vehiculo> vehiculoList2 = vehiculoService.buscarPorCiudad(vehiculo.getCiudad());
        for(Reserva r : reservaList) {
            if(r.getReservaEstado() == ReservaEstado.Pendiente) {
                List<Vehiculo> vehiculoList = ElegirFecha.ChooseDate(reservaService, vehiculoList2, r.getFechaFin(), r.getFechaFin());
                if (vehiculoList.isEmpty()) {
                    List<Pago> pagoList = pagoService.listarPorReserva(r);
                    Pago p1 = pagoList.get(0);
                    TarjetaCredito cliente = p1.getOrigen();
                    TarjetaCredito empresa = p1.getDestino();
                    p1.setOrigen(empresa);
                    p1.setDestino(cliente);
                    pagoService.guardarPago(p1);
                    if(r.getSeguro() == ReservaSeguro.No) {
                        Pago p2 = pagoList.get(1);
                        p2.setOrigen(empresa);
                        p2.setDestino(cliente);
                        pagoService.guardarPago(p2);
                    }
                    reservaService.delete(r);
                    Reserva reserva = UI.getCurrent().getSession().getAttribute(Reserva.class);
                    MailNotificationService.enviaEmail(UI.getCurrent().getSession().getAttribute(Usuario.class).getEmail(), "Reserva " + reserva.getCodigo() + " cancelada",
                            "Su reserva de fecha " + reserva.getFechaInicio() + " a fecha " + reserva.getFechaFin() +
                                    " se ha cancelado debido a falta de disponibilidad de vehiculos, si quiere hacer otra reserva entre en nuestra pagina.\n" +
                                    "Lo sentimos por las molestias.");
                } else {
                    r.setVehiculo(vehiculoList.get(0));
                    reservaService.save(r);
                }
            }
        }
    }
}
