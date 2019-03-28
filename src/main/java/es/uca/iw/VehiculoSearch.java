package es.uca.iw;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("VehiculoSearch")
public class VehiculoSearch {

    private Grid<Vehiculo> gVehiculos = new Grid<>(Vehiculo.class);
    private TextField filtertext = new TextField();

    public VehiculoSearch() {
        filtertext.setPlaceholder("flitrar por texto");
        filtertext.setClearButtonVisible(true);
        filtertext.setValueChangeMode(ValueChangeMode.EAGER);

        gVehiculos.setColumns("Marcca", "Modelo", "Precio");
    }

}
