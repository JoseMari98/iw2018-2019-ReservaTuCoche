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
import java.io.IOException;


@Route(value = "Info", layout = MainView.class)
public class VehiculoInfoView extends FormLayout {

    private Long id;
    private VehiculoService service;

    @Autowired
    public VehiculoInfoView(VehiculoService service) {
        this.service = service;
        this.id = UI.getCurrent().getSession().getAttribute(Long.class);
        Vehiculo v = service.buscarIdVehiculo(id).get();
        Html nombre = new Html("<h1>" + v.toString() + "</h1>");
        Html etiqueta2 = new Html("<h2>Información:</h2>");
        Label cuerpo = new Label("Precio:" + v.getPrecio() + " €/dia");
        Label cuerpo2 = new Label("Matrícula: " + v.getMatricula());
        VerticalLayout layout = new VerticalLayout(nombre, etiqueta2, cuerpo, cuerpo2);
        add(layout);
    }

}
