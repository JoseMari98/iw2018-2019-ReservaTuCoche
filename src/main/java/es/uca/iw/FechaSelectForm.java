package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDate;

public class FechaSelectForm extends FormLayout {
    private Button envia = new Button("Elegir");
    private FechaSelectView view;


    public FechaSelectForm(FechaSelectView view) {
        this.view = view;

        add(envia);
    }

    public void setReserva(VehiculoCiudad ciudad, LocalDate fechaIni, LocalDate fechaFin) {
        envia.addClickListener(e -> {
            Reserva r = new Reserva();
            r.setFechaInicio(fechaIni);
            r.setFechaFin(fechaFin);
            UI.getCurrent().getSession().setAttribute(VehiculoCiudad.class, ciudad);
            UI.getCurrent().getSession().setAttribute(Reserva.class, r);
            if(SecurityUtils.isUserLoggedIn()) {
                UI.getCurrent().navigate("search");
            } else
                Notification.show("¡Debe estar registrado!");
        });
    }
}
