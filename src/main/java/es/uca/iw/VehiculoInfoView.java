package es.uca.iw;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

@Route("Info")
public class VehiculoInfoView extends FormLayout implements HasUrlParameter<Long> {

    private Long id;
    private VehiculoService service;

    @Autowired
    public VehiculoInfoView(VehiculoService service) {
        this.service = service;
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        this.id = id;
        Vehiculo v = service.buscarIdVehiculo(id).get();
        Html nombre = new Html("<h1>" + v.toString() + "</h1>");
        Html etiqueta2 = new Html("<h2>Información:</h2>");
        Label cuerpo = new Label("Precio:" + v.getPrecio() + " €/dia");
        Label cuerpo2 = new Label("Matrícula: " + v.getMatricula());
        VerticalLayout layout = new VerticalLayout(nombre, etiqueta2, cuerpo, cuerpo2);
        add(layout);
    }
}
