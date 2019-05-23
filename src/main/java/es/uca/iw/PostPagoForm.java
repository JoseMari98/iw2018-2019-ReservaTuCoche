package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("pagoform")
public class PostPagoForm extends FormLayout {

    private TextField codigo = new TextField("Código reserva");
    private ComboBox<ReservaEstadoCoche> estadoCoche = new ComboBox<>("Estado del vehículo");
    private Button save = new Button("Añadir");
    private BeanValidationBinder<Reserva> binder = new BeanValidationBinder<>(Reserva.class);
    private ReservaService reservaService;
    private PagoService pagoService;

    public PostPagoForm(PagoService pagoService, ReservaService reservaService ){
        this.reservaService = reservaService;
        this.pagoService = pagoService;

        estadoCoche.setItems(ReservaEstadoCoche.values());
        estadoCoche.setRequired(true);
        codigo.setRequired(true);
        HorizontalLayout buttons = new HorizontalLayout(save);
        add(codigo, estadoCoche, buttons);
        Dialog dialog = new Dialog();

        binder.bindInstanceFields(this);

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
        Reserva reserva = reservaService.listarPorCodigo(Long.parseLong(codigo.getValue()));
        reserva.setEstadoCoche(estadoCoche.getValue());
        reserva.setReservaEstado(ReservaEstado.Finalizada);

        if(binder.validate().isOk()) {
            if(reserva.getSeguro() == ReservaSeguro.No) {
                Pago pago = new Pago();
                if(reserva.getEstadoCoche()!= ReservaEstadoCoche.Siniestrado)
                {
                    List<Pago> pagoList = pagoService.listarPorReserva(reserva);
                    pago.setReserva(reserva);
                    pago.setTipo(PagoTipo.Fianza);
                    for(int i = 0; i < 2; i++){
                        if(pagoList.get(i).getTipo() == PagoTipo.Fianza) {
                            if(reserva.getEstadoCoche() == ReservaEstadoCoche.Abollado) {
                                double cantidad = pagoList.get(i).getCantidad() * 0.5;
                                pago.setCantidad(cantidad);
                                pago.setOrigen(pagoList.get(i).getDestino());
                                pago.setDestino(pagoList.get(i).getOrigen());
                            } else{
                                double cantidad = pagoList.get(i).getCantidad();
                                pago.setCantidad(cantidad);
                                pago.setOrigen(pagoList.get(i).getOrigen());
                                pago.setDestino(pagoList.get(i).getDestino());
                            }
                        }
                    }
                }
                pagoService.guardarPago(pago);
            }
            reservaService.save(reserva);
            UI.getCurrent().navigate("");
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }
}
