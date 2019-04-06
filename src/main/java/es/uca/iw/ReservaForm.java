package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
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
            }
        });

        fechaFin.addValueChangeListener(event -> {
            LocalDate fin = event.getValue();
            LocalDate inicio = fechaInicio.getValue();
            if (fin != null) {
                fechaInicio.setMax(fin.minusDays(1));
            }
        });

        save.addClickListener(click -> {
            Div mensaje = new Div();
            if (!validation.getValue()) {
                mensaje.setText("Se deben aceptar los términos y condiciones para proceder con la reserva.");
            } else {
                mensaje.setText("Formulario correcto. Registro de la reserva pendiente de implementar.");
            }
        });

        delete.addClickListener(click -> {
            Div mensaje = new Div();
            mensaje.setText("\"Volver a la página anterior\" pendiente de implementar.");
        });
    }

}