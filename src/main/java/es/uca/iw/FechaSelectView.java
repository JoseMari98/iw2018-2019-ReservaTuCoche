package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.formlayout.FormLayout;

import java.time.LocalDate;

@Route(value = "", layout = MainView.class)
public class FechaSelectView extends FormLayout {
    private DatePicker fechaInicio;
    private DatePicker fechaFin;

    private ComboBox<VehiculoCiudad> ciudad = new ComboBox<>();
    private Button envia = new Button("Elegir");

    public FechaSelectView() {
        fechaInicio = new DatePicker();
        fechaFin = new DatePicker();

        LocalDate now = LocalDate.now();

        fechaInicio.setMin(now);
        fechaInicio.setRequired(true);
        fechaFin.setRequired(true);
        fechaFin.setMin(now);
        ciudad.setItems(VehiculoCiudad.values());
        ciudad.setLabel("Ciudad");
        fechaInicio.setLabel("Fecha de inicio");
        fechaFin.setLabel("Fecha final");
        ciudad.setRequired(true);
        HorizontalLayout layout = new HorizontalLayout(fechaInicio, fechaFin, ciudad);

        envia.addClickListener(e -> {
            Reserva r = new Reserva();
            UI.getCurrent().getSession().setAttribute(VehiculoCiudad.class,ciudad.getValue());
            r.setFechaInicio(fechaInicio.getValue());
            r.setFechaFin(fechaFin.getValue());
            UI.getCurrent().getSession().setAttribute(Reserva.class, r);
            if(SecurityUtils.isUserLoggedIn()) {
                UI.getCurrent().navigate("search");
            } else
                Notification.show("¡Debe estar registrado!");
        });

        add(layout, envia);
    }
}
