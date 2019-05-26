package es.uca.iw;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ElegirFecha {
    public static List<Vehiculo> ChooseDate(ReservaService reservaService, List<Vehiculo> list, LocalDate fechaI, LocalDate fechaF) {
        ArrayList<Vehiculo> listaVehiculo = new ArrayList<>();
        boolean encontrado = false;
        for (Vehiculo vehiculo : list) {
            List<Reserva> reservas = reservaService.listarReservaPorVehiculo(vehiculo);
            Iterator<Reserva> it = reservas.iterator();
            while(it.hasNext() && !encontrado) {
                Reserva r = it.next();
                LocalDate fechaReservaInicio = r.getFechaInicio();
                LocalDate fechaReservaFin = r.getFechaFin();

                boolean prueba1 = fechaI.isAfter(fechaReservaInicio.minusDays(1)) && fechaI.isBefore(fechaReservaFin.plusDays(1));
                boolean prueba2 = fechaF.isAfter(fechaReservaInicio.minusDays(1)) && fechaF.isBefore(fechaReservaFin.plusDays(1));
                boolean prueba3 = fechaReservaInicio.isAfter(fechaI.minusDays(1)) && fechaReservaInicio.isBefore(fechaF.plusDays(1));
                boolean prueba4 = fechaReservaFin.isAfter(fechaI.minusDays(1)) && fechaReservaFin.isBefore(fechaF.plusDays(1));

                if (prueba1 || prueba2 || prueba3 || prueba4) {
                    encontrado = true;
                }

                it.remove();
            }
            if(!encontrado)
                listaVehiculo.add(vehiculo);
            encontrado = false;

        }
        return listaVehiculo;
    }
}