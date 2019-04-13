package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route("reservaform")

public class ReservaForm extends FormLayout {

    private DatePicker fechaInicio = new DatePicker("Fecha de recogida");
    private DatePicker fechaFin = new DatePicker("Fecha de entrega");
    private Checkbox validation = new Checkbox("Acepto los términos y condiciones del sitio web.");

    private Button save = new Button("Reservar");
    private Button delete = new Button("Cancelar");

    private Binder<Reserva> binder = new Binder<>(Reserva.class);
    private ReservaService service;

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
                notificate("Se deben aceptar los términos y condiciones para proceder. ");
            } else if (fechaInicio.getValue() == null) {
                notificate("No se ha indicado la fecha de recogida del vehículo. ");
            } else if (fechaFin.getValue() == null) {
                notificate("No se ha indicado la fecha de entrega del vehículo. ");
            } else {
                NativeButton botonSi = new NativeButton("Sí");
                NativeButton botonNo = new NativeButton("No");
                Notification notification = new Notification(new Label("¿Confirmar reserva? "), botonSi, new Label(" "), botonNo);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
                botonSi.addClickListener(event -> {
                    notification.close();
                    save();
                });
                botonNo.addClickListener(event -> notification.close());
            }
        });

        delete.addClickListener(click -> {
            UI.getCurrent().navigate("principal");
        });
    }

    private void save() {
        NativeButton botonNotif = new NativeButton("X");
        Notification notifInt = new Notification(new Label("La reserva se ha realizado de forma exitosa. "), botonNotif);
        notifInt.setDuration(4000);
        notifInt.setPosition(Notification.Position.MIDDLE);
        notifInt.open();
        botonNotif.addClickListener(eventInt -> notifInt.close());
        //Reserva reserva = binder.getBean();
        //service.save(reserva);
        UI.getCurrent().navigate("principal");
    }

    private void notificate(String sNotif) {
        NativeButton botonNotif = new NativeButton("X");
        Notification notification = new Notification(new Label(sNotif), botonNotif);
        notification.setDuration(4000);
        notification.setPosition(Notification.Position.MIDDLE);
        notification.open();
        botonNotif.addClickListener(event -> notification.close());
    }

}