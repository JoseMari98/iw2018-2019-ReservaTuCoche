package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

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
        add(fechaInicio, fechaFin, validation, buttons);

        setSizeFull();

    }

}