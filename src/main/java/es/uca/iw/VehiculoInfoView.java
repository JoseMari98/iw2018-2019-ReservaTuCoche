package es.uca.iw;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

@Route("Info")
public class VehiculoInfoView implements HasUrlParameter<Long> {

    private Long id;
    private Label nombre;
    @Autowired
    public VehiculoInfoView(VehiculoService service, VehiculoModeloService modeloService, VehiculoMarcaService marcaService) throws IOException {
        nombre = new Label(service.buscarIdVehiculo(id).toString());
        VerticalLayout layout = new VerticalLayout(nombre);
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        this.id = id;
    }
}
