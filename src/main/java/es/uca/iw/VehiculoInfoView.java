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


@Route(value = "Info", layout = MainView.class)
public class VehiculoInfoView extends VerticalLayout {

    private Long id;
    private VehiculoService service;
    private Button volver = new Button("Regresar");

    @Autowired
    public VehiculoInfoView(VehiculoService service) {
        this.service = service;
        this.id = UI.getCurrent().getSession().getAttribute(Long.class);
        Vehiculo v = service.buscarIdVehiculo(id).get();
        Html nombre = new Html("<h1>" + v.toString() + "</h1>");
        Html etiqueta2 = new Html("<h2>Información:</h2>");
        Label cuerpo = new Label("Precio: " + v.getPrecio() + " €/dia");
        Label marca = new Label("El vehiculo es: " + v.getMarca().getMarca() + " " + v.getTipo().getTipo());
        Label cuerpo4 = new Label("Tipo de motor: " + v.getMotor().toString());
        Label cuerpo5 = new Label("Aire acondicionado: " + v.getAc().toString());
        Label cuerpo6 = new Label("Ciudad: " + v.getCiudad().toString());
        Label cuerpo7 = new Label("Nº de puertas: " + v.getPuertas().intValue());
        Label cuerpo8 = new Label("Nº de plazas: " + v.getPlazas().intValue());
        VerticalLayout layout = new VerticalLayout(nombre, etiqueta2, cuerpo, marca,cuerpo4, cuerpo6, cuerpo7, cuerpo8, cuerpo5, volver);
        add(layout);
        volver.addClickListener(e -> UI.getCurrent().navigate("search"));
    }

}
