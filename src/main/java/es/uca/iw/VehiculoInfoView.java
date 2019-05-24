package es.uca.iw;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.io.IOException;


@Route(value = "Info", layout = MainView.class)
public class VehiculoInfoView extends AbstractView {

    private Long id;
    private VehiculoService service;

    @Autowired
    public VehiculoInfoView(VehiculoService service) {
        this.service = service;
        this.id = UI.getCurrent().getSession().getAttribute(Long.class);
        Vehiculo v = service.buscarIdVehiculo(id).get();
        Html nombre = new Html("<h1>" + v.toString() + "</h1>");
        Html etiqueta2 = new Html("<h2>Información:</h2>");
        Label cuerpo = new Label("Precio: " + v.getPrecio() + " €/dia");
        Label cuerpo2 = new Label("Tipo: " + v.getModelo().getModelo());
        Label cuerpo3 = new Label("Matrícula: " + v.getMatricula());
        Label cuerpo4 = new Label("Tipo de motor: " + v.getMotor().toString());
        Label cuerpo5 = new Label("Aire acondicionado: " + v.getAc().toString());
        Label cuerpo6 = new Label("Ciudad: " + v.getCiudad().toString());
        Label cuerpo7 = new Label("Nº de puertas: " + v.getPuertas().intValue());
        Label cuerpo8 = new Label("Nº de plazas: " + v.getPlazas().intValue());
        VerticalLayout layout = new VerticalLayout(nombre, etiqueta2, cuerpo, cuerpo2, cuerpo3,cuerpo4, cuerpo6, cuerpo7, cuerpo8, cuerpo5);
        add(layout);
    }

}
