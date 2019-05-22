package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class VehiculoSearchForm extends FormLayout {
    private Button reserva = new Button("Reservar");
    private Button info = new Button("Mas información");
    private VehiculoSearch view;
    private VehiculoService service;
    private VehiculoMarcaService maService;
    private VehiculoModeloService moService;

    public VehiculoSearchForm(VehiculoSearch view, VehiculoService service, VehiculoModeloService moService, VehiculoMarcaService maService) {
        this.view = view;
        this.service = service;
        this.moService = moService;
        this.maService = maService;

        HorizontalLayout botones = new HorizontalLayout(reserva, info);

        add(botones);

    }

    public void setReserva(Long id) {
        reserva.addClickListener(event -> {
            if (SecurityUtils.isUserLoggedIn()) {
                UI.getCurrent().navigate("reservaform/"  + id);
            } else
                Notification.show("Debes estar registrado!");
        });

        info.addClickListener(event -> {
            UI.getCurrent().navigate("Info/"  + id);
        });
    }
}
