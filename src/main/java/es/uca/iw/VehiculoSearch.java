package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static org.apache.el.lang.ELArithmetic.add;

@Route("Search")
public class VehiculoSearch extends FormLayout {

    private Grid<Vehiculo> gVehiculos = new Grid<>(Vehiculo.class);
    private TextField filtertext = new TextField();
    private VehiculoService service;
    private Button search = new Button("Search!");

    @Autowired
    public VehiculoSearch(VehiculoService serv) {
        service = serv;
        ArrayList<Vehiculo> listaVehiculo = new ArrayList<>();
        for(Vehiculo vehiculo : service.listarVehiculo())
            listaVehiculo.add(vehiculo);

        filtertext.setPlaceholder("filtrar por texto");
        filtertext.setClearButtonVisible(true);
        filtertext.setValueChangeMode(ValueChangeMode.EAGER);

        HorizontalLayout buscar = new HorizontalLayout(filtertext, search);

        gVehiculos.setColumns("marca", "modelo", "precio");

        gVehiculos.setItems(listaVehiculo);

        VerticalLayout listado = new VerticalLayout(buscar, gVehiculos);

        add(listado);
    }

}
