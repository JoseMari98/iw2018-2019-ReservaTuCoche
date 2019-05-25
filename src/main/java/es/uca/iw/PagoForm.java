package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class PagoForm extends FormLayout {
    TextField numeroTarjeta = new TextField("Numero de tarjeta de credito");
    TextField numeroSeguridad = new TextField("Numero de seguridad");
    DatePicker fechaCaducidad = new DatePicker("Fecha de caducidad");
    RadioButtonGroup<ReservaSeguro> seguro = new RadioButtonGroup<>();
    private Button save = new Button("Pagar");
    private PagoView pagoView;
    private BeanValidationBinder<TarjetaCredito> binder = new BeanValidationBinder<>(TarjetaCredito.class);
    private PagoService pagoService;
    private ReservaService reservaService;
    private TarjetaCreditoService tarjetaService;
    H1 precio = new H1(Double.toString(UI.getCurrent().getSession().getAttribute(Reserva.class).getPrecioTotal()) + "€");


    public PagoForm(PagoView pagoView, PagoService pagoService, TarjetaCreditoService tarjetaService, ReservaService reservaService) {
        this.pagoService = pagoService;
        this.tarjetaService = tarjetaService;
        this.pagoView = pagoView;
        this.reservaService = reservaService;
        TarjetaCredito tarjetaCredito = new TarjetaCredito();
        tarjetaCredito.setUsuario(UI.getCurrent().getSession().getAttribute(Usuario.class));
        binder.setBean(tarjetaCredito);

        numeroTarjeta.setRequired(true);
        numeroSeguridad.setRequired(true);
        fechaCaducidad.setRequired(true);
        numeroTarjeta.addValueChangeListener(e -> {
           if(tarjetaService.listarPorNumero(numeroTarjeta.getValue()) != null &&
                   tarjetaService.listarPorNumero(numeroTarjeta.getValue()).getUsuario().getId() == UI.getCurrent().getSession().getAttribute(Usuario.class).getId()) {
               binder.setBean(tarjetaService.listarPorNumero(numeroTarjeta.getValue()));
               Notification.show("Tarjeta autocompletada", 5000, Notification.Position.MIDDLE);

               /*Dialog d = new Dialog();
               d.open();
               Label l = new Label("Desea autocompletar");
               Button confirmButton = new Button("Confirmar", event -> {
                   binder.setBean(tarjetaService.listarPorNumero(numeroTarjeta.getValue()));
                   d.close();
               });
               confirmButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
               Button cancelButton = new Button("Cancelar", event -> d.close());
               d.add(l, confirmButton,cancelButton);*/
           }
        });
        seguro.setLabel("Seguro para el vehiculo, 150 euros mas");
        seguro.setRequired(true);
        seguro.setItems(ReservaSeguro.values());
        seguro.setValue(ReservaSeguro.No);
        seguro.addValueChangeListener(e -> cambioPrecio(seguro.getValue()));
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(numeroTarjeta, numeroSeguridad, fechaCaducidad, seguro, precio, save);

        binder.bindInstanceFields(this);

        Dialog dialog = new Dialog();

        Button confirmButton = new Button("Confirmar", event -> {
            save();
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

    public void save() {
        if(binder.validate().isOk()) {
            tarjetaService.guardarTarjeta(binder.getBean());
            UI.getCurrent().getSession().getAttribute(Reserva.class).setSeguro(seguro.getValue());
            reservaService.save(UI.getCurrent().getSession().getAttribute(Reserva.class));
            Pago pagoReserva = new Pago();
            pagoReserva.setReserva(UI.getCurrent().getSession().getAttribute(Reserva.class));
            pagoReserva.setTipo(PagoTipo.Reserva);
            pagoReserva.setDestino(tarjetaService.buscarIdTarjeta(new Long(1)).get());
            pagoReserva.setOrigen(binder.getBean());
            pagoReserva.setCantidad(UI.getCurrent().getSession().getAttribute(Reserva.class).getPrecioTotal());
            if(UI.getCurrent().getSession().getAttribute(Reserva.class).getSeguro() == ReservaSeguro.No){
                Pago pagoFianza = new Pago();
                pagoFianza.setReserva(UI.getCurrent().getSession().getAttribute(Reserva.class));
                pagoFianza.setTipo(PagoTipo.Fianza);
                pagoFianza.setDestino(tarjetaService.buscarIdTarjeta(new Long(1)).get());
                pagoFianza.setOrigen(binder.getBean());
                pagoFianza.setCantidad(500.0);
                pagoService.guardarPago(pagoFianza);
            }
            pagoService.guardarPago(pagoReserva);
            UI.getCurrent().navigate("ReservaInfo");
        } else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    private void cambioPrecio(ReservaSeguro reservaSeguro) {
        if(ReservaSeguro.Si == reservaSeguro) {
            this.remove(precio, save);
            UI.getCurrent().getSession().getAttribute(Reserva.class).setPrecioTotal(UI.getCurrent().getSession().getAttribute(Reserva.class).getPrecioTotal() + 150.0);
            precio = new H1(Double.toString(UI.getCurrent().getSession().getAttribute(Reserva.class).getPrecioTotal()) + "€");
            this.add(precio, save);
        } else {
            this.remove(precio, save);
            UI.getCurrent().getSession().getAttribute(Reserva.class).setPrecioTotal(UI.getCurrent().getSession().getAttribute(Reserva.class).getPrecioTotal() - 150.0);
            precio = new H1(Double.toString(UI.getCurrent().getSession().getAttribute(Reserva.class).getPrecioTotal()) + "€");
            this.add(precio, save);
        }
    }
}