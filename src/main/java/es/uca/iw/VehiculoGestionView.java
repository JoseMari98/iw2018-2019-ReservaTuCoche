package es.uca.iw;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("GestionVehiculo")
public class VehiculoGestionView extends VerticalLayout {
    private Grid<Vehiculo> grid = new Grid<>(Vehiculo.class);
    private TextField filterText = new TextField();
    private VehiculoForm form = new VehiculoForm();

    public VehiculoGestionView() {

    }
}
