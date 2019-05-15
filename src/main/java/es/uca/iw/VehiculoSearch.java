package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.Stream;

@Route("Search")
public class VehiculoSearch extends FormLayout {

    private Grid<Vehiculo> gVehiculos = new Grid<>(Vehiculo.class);
    private TextField filtertext = new TextField();
    private VehiculoService service;
    private Button reserva = new Button("Reservar");
    private RadioButtonGroup<VehiculoMarca> buscaMarca = new RadioButtonGroup<>();
    private RadioButtonGroup<VehiculoModelo> buscaModelo = new RadioButtonGroup<>();

    @Autowired
    public VehiculoSearch(VehiculoService serv, VehiculoMarcaService serviceMarca, VehiculoModeloService serviceModelo) {
        service = serv;
        buscaMarca.setLabel("Marca");
        buscaModelo.setLabel("Modelo");
        final String[] query = {"", ""};
        ArrayList<Vehiculo> listaVehiculo = new ArrayList<>();
        ArrayList<VehiculoMarca> listaMarca = new ArrayList<>();
        ArrayList<VehiculoModelo> listaModelo = new ArrayList<>();
        for(Vehiculo vehiculo : service.listarVehiculo())
            listaVehiculo.add(vehiculo);

        for (VehiculoMarca marca : serviceMarca.listarMarca()) {
            listaMarca.add(marca);
        }

        for (VehiculoModelo modelo : serviceModelo.listarModelo()) {
            listaModelo.add(modelo);
        }

        buscaMarca.setItems(listaMarca);

        buscaMarca.setRenderer(new ComponentRenderer<>(mar -> new Anchor("localhost/" + mar.getId(), mar.getMarca())));

        buscaMarca.addValueChangeListener(event -> {
            query[0] = buscaMarca.getValue().getMarca();
            updateList(query);
        });

        buscaModelo.setItems(listaModelo);

        buscaModelo.setRenderer(new ComponentRenderer<>(mod -> new Anchor("localhost/" + mod.getId(), mod.getModelo())));

        buscaModelo.addValueChangeListener( event -> {
            query[1] = buscaModelo.getValue().getModelo();
            updateList(query);
        });

        gVehiculos.setColumns("marca.marca", "modelo.modelo", "precio");

        gVehiculos.setItems(listaVehiculo);

        reserva.addClickListener(event -> {
            if (SecurityUtils.isUserLoggedIn()) {
                UI.getCurrent().navigate("reservaform/"  + gVehiculos.asSingleSelect().getValue().getId());
            } else
                Notification.show("Debes estar registrado!");
        });

        VerticalLayout filters = new VerticalLayout(buscaMarca, buscaModelo);

        VerticalLayout reservar = new VerticalLayout(gVehiculos, reserva);

        HorizontalLayout lista = new HorizontalLayout(reservar, filters);

        add(lista);

        setSizeFull();
    }

    public void updateList( String[] marca) {
        if(marca[0] == "" && marca[1] == "")
            gVehiculos.setItems(service.listarVehiculo());
        else {
            if (marca[0] != "" && marca[1] == "")
                gVehiculos.setItems(service.listarVehiculoPorMarca(marca[0]));
            else {
                if(marca[0] == "" && marca[1] != "")
                    gVehiculos.setItems(service.listarVehiculoPorModelo(marca[1]));
                else {
                    gVehiculos.setItems(service.listarVehiculoPorMarcaYModelo(marca[0], marca[1]));
                }
            }
        }
    }
}

