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

import static org.apache.el.lang.ELArithmetic.add;

@Route("VehiculoSearch")
public class VehiculoSearch extends FormLayout {

    private Grid<Vehiculo> gVehiculos = new Grid<>(Vehiculo.class);
    private TextField filtertext = new TextField();
    public VehiculoSearch() {

        filtertext.setPlaceholder("filtrar por texto");
        filtertext.setClearButtonVisible(true);
        filtertext.setValueChangeMode(ValueChangeMode.EAGER);

        gVehiculos.setColumns("marca", "modelo", "precio");
        VerticalLayout Busqueda = new VerticalLayout(filtertext, gVehiculos);

        add(Busqueda);
    }

}
