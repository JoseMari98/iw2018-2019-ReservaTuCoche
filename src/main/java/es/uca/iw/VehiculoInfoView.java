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
    private Html nombre;
    private VehiculoService service;

    @Autowired
    public VehiculoInfoView(VehiculoService service) {
        this.service = service;
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        this.id = id;
        nombre = new Html("<h1>" + service.buscarIdVehiculo(id).get().toString() + "</h1>");
        VerticalLayout layout = new VerticalLayout(nombre);
        add(layout);
    }
}
