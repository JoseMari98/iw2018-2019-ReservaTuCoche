package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
    private ComboBox<VehiculoMarca> marca = new ComboBox<>("Marca");
    private Button search = new Button("Buscar por marca");
    private Button reserva = new Button("Reservar");

    @Autowired
    public VehiculoSearch(VehiculoService serv, VehiculoMarcaService serviceMarca) {
        service = serv;
        ArrayList<Vehiculo> listaVehiculo = new ArrayList<>();
        for(Vehiculo vehiculo : service.listarVehiculo())
            listaVehiculo.add(vehiculo);

        /*filtertext.setPlaceholder("filtrar por marca");
        filtertext.setClearButtonVisible(true);
        filtertext.setValueChangeMode(ValueChangeMode.EAGER);

        filtertext.addValueChangeListener(e->updateList());*/

        marca.setItemLabelGenerator(VehiculoMarca::getMarca);
        marca.setItems(serviceMarca.listarMarca());

        search.addClickListener(e->updateList());


        gVehiculos.setColumns("marca.marca", "modelo", "precio");

        gVehiculos.setItems(listaVehiculo);

        HorizontalLayout buscar = new HorizontalLayout(marca, search);

        VerticalLayout reservar = new VerticalLayout(gVehiculos, reserva);

        HorizontalLayout lista = new HorizontalLayout(reservar, buscar);

        add(lista);

        setSizeFull();
    }

    public void updateList() {
        if(marca.isEmpty())
            gVehiculos.setItems(service.listarVehiculo());
        else
            gVehiculos.setItems(service.listarVehiculoPorMarca(marca.getValue().getMarca()));
    }
}
