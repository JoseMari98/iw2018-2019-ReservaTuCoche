package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route("reservaform")

public class ReservaForm extends FormLayout {

    private DatePicker fechaInicio = new DatePicker("Fecha de recogida");
    private DatePicker fechaFin = new DatePicker("Fecha de entrega");
    private Checkbox validation = new Checkbox("Acepto los términos y condiciones del sitio web.");

    private Button save = new Button("Reservar");
    private Button delete = new Button("Cancelar");

    public ReservaForm() {
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        LocalDate actual = LocalDate.now();
        fechaInicio.setMin(actual);
        fechaInicio.setMax(actual.plusYears(1));
        fechaFin.setMin(actual);
        fechaFin.setMax(actual.plusYears(1));
        add(fechaInicio, fechaFin, validation, buttons);

        setSizeFull();

        fechaInicio.addValueChangeListener(event -> {
            LocalDate inicio = event.getValue();
            LocalDate fin = fechaFin.getValue();
            if (inicio != null) {
                fechaFin.setMin(inicio.plusDays(1));
            } else {
                fechaFin.setMin(LocalDate.now());
            }
        });

        fechaFin.addValueChangeListener(event -> {
            LocalDate fin = event.getValue();
            LocalDate inicio = fechaInicio.getValue();
            if (fin != null) {
                fechaInicio.setMax(fin.minusDays(1));
            } else {
                fechaInicio.setMax(LocalDate.now().plusYears(1));
            }
        });

        save.addClickListener(click -> {
            if (!validation.getValue()) {
                NativeButton botonNotif = new NativeButton("X");
                Notification notification = new Notification(new Label("Se deben aceptar los términos y condiciones para proceder."), botonNotif);
                notification.setDuration(5000);
                notification.setPosition(Notification.Position.MIDDLE);
                botonNotif.addClickListener(event -> notification.close());
            } else if (fechaInicio.getValue() == null) {
                NativeButton botonNotif = new NativeButton("X");
                Notification notification = new Notification(new Label("No se ha indicado la fecha de recogida del vehículo."), botonNotif);
                notification.setDuration(5000);
                notification.setPosition(Notification.Position.MIDDLE);
                botonNotif.addClickListener(event -> notification.close());
            } else if (fechaFin.getValue() == null) {
                NativeButton botonNotif = new NativeButton("X");
                Notification notification = new Notification(new Label("No se ha indicado la fecha de entrega del vehículo."), botonNotif);
                notification.setDuration(5000);
                notification.setPosition(Notification.Position.MIDDLE);
                botonNotif.addClickListener(event -> notification.close());
            } else {
                NativeButton botonNotif = new NativeButton("X");
                Notification notification = new Notification(new Label("La reserva se ha realizado de forma exitosa."), botonNotif);
                notification.setDuration(5000);
                notification.setPosition(Notification.Position.MIDDLE);
                botonNotif.addClickListener(event -> notification.close());
                UI.getCurrent().navigate("principal");
            }
        });

        delete.addClickListener(click -> {
            UI.getCurrent().navigate("principal");
        });
    }

}