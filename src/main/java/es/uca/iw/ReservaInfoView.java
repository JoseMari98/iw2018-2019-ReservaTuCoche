package es.uca.iw;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import javax.swing.*;
import java.io.IOException;


@Route(value = "ReservaInfo", layout = MainView.class)
@Secured("User")
public class ReservaInfoView extends AbstractView {

    private Vehiculo vehiculo;
    private Reserva reserva;
    private ReservaService service;
    private Button volver = new Button("Regresar");

    @Autowired
    public ReservaInfoView(ReservaService service) {
        if(UI.getCurrent().getSession().getAttribute(Usuario.class) != null) {
            this.service = service;
            this.reserva = UI.getCurrent().getSession().getAttribute(Reserva.class);
            this.vehiculo = UI.getCurrent().getSession().getAttribute(Vehiculo.class);

            Html etiqueta2 = new Html("<h2>Información de la reserva:</h2>");
            Label marca = new Label("El vehiculo es: " + vehiculo.getMarca().getMarca() + " " + vehiculo.getTipo().getTipo());
            Label codigo = new Label("El codigo es: " + reserva.getCodigo());
            Label fechas = new Label("La fecha desde: " + reserva.getFechaInicio() + " hasta " + reserva.getFechaFin());
            Label precio = new Label("El precio es de: " + reserva.getPrecioTotal() + " €");
            VerticalLayout layout = new VerticalLayout(etiqueta2, marca, codigo, fechas, precio, volver);
            add(layout);
            volver.addClickListener(e -> UI.getCurrent().navigate(""));
        } else
            UI.getCurrent().navigate("");
    }

}