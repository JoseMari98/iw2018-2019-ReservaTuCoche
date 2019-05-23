package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class PagoForm extends FormLayout {
    TextField numeroTarjeta = new TextField("Numero de tarjeta de credito");
    TextField numeroSeguridad = new TextField("Numero de seguridad");
    DatePicker fechaCaducidad = new DatePicker("Fecha de caducidad");
    ComboBox<ReservaSeguro> seguro = new ComboBox<>("Seguro");
    private Button save = new Button("Pagar");
    private PagoView pagoView;
    private BeanValidationBinder<TarjetaCredito> binder = new BeanValidationBinder<>(TarjetaCredito.class);
    private PagoService pagoService;
    private TarjetaCreditoService tarjetaService;

    public PagoForm(PagoView pagoView, PagoService pagoService, TarjetaCreditoService tarjetaService) {
        this.pagoService = pagoService;
        this.tarjetaService = tarjetaService;
        this.pagoView = pagoView;

        numeroTarjeta.setRequired(true);
        numeroSeguridad.setRequired(true);
        fechaCaducidad.setRequired(true);
        seguro.setRequired(true);
        seguro.setItems(ReservaSeguro.values());
        seguro.setPreventInvalidInput(true);
        seguro.setValue(ReservaSeguro.No);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(numeroTarjeta, numeroSeguridad, fechaCaducidad, seguro, save);

        binder.bindInstanceFields(this);

        Dialog dialog = new Dialog();

        Button confirmButton = new Button("Confirmar", event -> {
            //save();
            dialog.close();
        });
        confirmButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        Button cancelButton = new Button("Cancelar", event -> {
            dialog.close();
        });
        dialog.add(confirmButton, cancelButton);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addClickListener(event -> dialog.open());
    }

    /*public void save() {
        Pago pago = new Pago();
        pago.setFechaCaducidad(fechaCaducidad.getValue());
        pago.setNumeroSeguridad(numeroSeguridad.getValue());
        pago.setPropietario(propietario.getValue());
        pago.setTarjetaCredito(tarjetaCredito.getValue());
        //meter en la reserva de la sesion el seguro
        if(binder.validate().isOk()) {
            pagoService.guardarPago(pago);
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }*/
}