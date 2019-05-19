package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class PagoForm extends FormLayout {
    TextField tarjetaCredito = new TextField("Numero de tarjeta de credito");
    TextField propietario = new TextField("Nombre y apellidos del propietario de la tarjeta");
    TextField numeroSeguridad = new TextField("Numero de seguridad");
    DatePicker fechaCaducidad = new DatePicker("Fecha de caducidad");
    private Button save = new Button("Pagar");
    private Button cancelar = new Button("Cancelar");
    private PagoView pagoView;
    private BeanValidationBinder<Pago> binder = new BeanValidationBinder<>(Pago.class);
    private PagoService pagoService;

    public PagoForm(PagoView pagoView, PagoService pagoService) {
        this.pagoService = pagoService;
        this.pagoView = pagoView;

        tarjetaCredito.setRequired(true);
        propietario.setRequired(true);
        numeroSeguridad.setRequired(true);
        fechaCaducidad.setRequired(true);

        HorizontalLayout buttons = new HorizontalLayout(save, cancelar);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(tarjetaCredito, propietario, numeroSeguridad, fechaCaducidad, buttons);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
    }

    public void save() {
        Pago pago = new Pago();
        pago.setFechaCaducidad(fechaCaducidad.getValue());
        pago.setNumeroSeguridad(numeroSeguridad.getValue());
        pago.setPropietario(propietario.getValue());
        pago.setTarjetaCredito(tarjetaCredito.getValue());
        if(binder.validate().isOk()) {
            pagoService.guardarPago(pago);
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }
}
