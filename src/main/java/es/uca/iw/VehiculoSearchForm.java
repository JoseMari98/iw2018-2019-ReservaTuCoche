package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.time.LocalDate;
import java.util.Random;

public class VehiculoSearchForm extends FormLayout {
    private Button reserva = new Button("Reservar");
    private Button info = new Button("Mas información");
    private VehiculoSearch view;
    private VehiculoService service;
    private VehiculoMarcaService maService;
    private VehiculoModeloService moService;
    private ReservaService reservaService;

    public VehiculoSearchForm(VehiculoSearch view, VehiculoService service, VehiculoModeloService moService, VehiculoMarcaService maService, ReservaService reservaService) {
        this.view = view;
        this.service = service;
        this.moService = moService;
        this.maService = maService;
        this.reservaService = reservaService;

        HorizontalLayout botones = new HorizontalLayout(reserva, info);

        add(botones);

    }

    public void setReserva(Long id) {
        Reserva r = new Reserva();
        LocalDate now = LocalDate.now();
        r.setFechaFin(now.plusDays(7));
        r.setFechaInicio(now);

        r.setVehiculo(service.buscarIdVehiculo(id).get());

        Random random = new Random();

        while(reservaService.listarPorCodigo(random.nextLong()) != null) {
            random = new Random();
        }

        r.setCodigo(random.nextLong());
        reserva.addClickListener(event -> {
            UI.getCurrent().getSession().setAttribute(Reserva.class, r);
            UI.getCurrent().navigate("PagoView");
        });

        info.addClickListener(event -> {
            UI.getCurrent().navigate("Info/"  + id);
        });
    }
}
