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
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@Route(value="search", layout = MainView.class)
@Secured("User")
public class VehiculoSearch extends AbstractView {

    private Grid<Vehiculo> gVehiculos = new Grid<>(Vehiculo.class);
    private VehiculoService service;
    private VehiculoMarcaService serviceMarca;
    private VehiculoModeloService serviceModelo;
    private VehiculoSearchForm form;
    private RadioButtonGroup<VehiculoMarca> buscaMarca = new RadioButtonGroup<>();
    private RadioButtonGroup<VehiculoModelo> buscaModelo = new RadioButtonGroup<>();
    private ReservaService reservaService;

    private LocalDate fechaFin;
    private LocalDate fechaInicio;

    @Autowired
    public VehiculoSearch(VehiculoService serv, VehiculoMarcaService serviceMarca, VehiculoModeloService serviceModelo, ReservaService reservaService) {
        service = serv;
        this.serviceMarca = serviceMarca;
        this.serviceModelo = serviceModelo;
        this.reservaService = reservaService;
        form = new VehiculoSearchForm(this, serv, serviceModelo, serviceMarca, reservaService);
        buscaMarca.setLabel("Marca");
        buscaModelo.setLabel("Modelo");


        fechaInicio = UI.getCurrent().getSession().getAttribute(Reserva.class).getFechaInicio();
        fechaFin = UI.getCurrent().getSession().getAttribute(Reserva.class).getFechaFin();
        VehiculoCiudad ciudad = UI.getCurrent().getSession().getAttribute(VehiculoCiudad.class);

        final String[] query = {"", ""};
        ArrayList<Vehiculo> listaVehiculo = new ArrayList<>();
        ArrayList<VehiculoMarca> listaMarca = new ArrayList<>();
        ArrayList<VehiculoModelo> listaModelo = new ArrayList<>();
        for(Vehiculo vehiculo : this.ChooseDate(service.buscarPorCiudad(ciudad),fechaInicio,fechaFin))
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
            updateList(query, ciudad);
        });

        buscaModelo.setItems(listaModelo);

        buscaModelo.setRenderer(new ComponentRenderer<>(mod -> new Anchor("localhost/" + mod.getId(), mod.getModelo())));

        buscaModelo.addValueChangeListener( event -> {
            query[1] = buscaModelo.getValue().getModelo();
            updateList(query,ciudad);
        });

        gVehiculos.setColumns("marca.marca", "modelo.modelo", "precio");

        gVehiculos.setItems(listaVehiculo);

        HorizontalLayout filters = new HorizontalLayout(buscaMarca, buscaModelo);

        VerticalLayout reservar = new VerticalLayout(gVehiculos, form);

        VerticalLayout lista = new VerticalLayout(filters, reservar);

        add(lista);

        setSizeFull();

        Reserva r = new Reserva();
        r.setFechaInicio(fechaInicio);
        r.setFechaFin(fechaFin);

        gVehiculos.asSingleSelect().addValueChangeListener( e -> {
            UI.getCurrent().getSession().setAttribute(Reserva.class, r);
            form.setReserva(gVehiculos.asSingleSelect().getValue().getId());
        });


    }

    public List<Vehiculo> ChooseDate(List<Vehiculo> list, LocalDate fechaI, LocalDate fechaF) {
        ArrayList<Vehiculo> listaVehiculo = new ArrayList<>();
        boolean encontrado = false;
        for (Vehiculo vehiculo : list) {
            List<Reserva> reservas = reservaService.listarReservaPorVehiculo(vehiculo);
            Iterator<Reserva> it = reservas.iterator();
            while(it.hasNext() && !encontrado) {
                LocalDate fechaReservaInicio = it.next().getFechaInicio();
                LocalDate fechaReservaFin = it.next().getFechaFin();

                boolean prueba1 = fechaI.isAfter(fechaReservaInicio) && fechaI.isBefore(fechaReservaFin);
                boolean prueba2 = fechaF.isAfter(fechaReservaInicio) && fechaF.isBefore(fechaReservaFin);
                boolean prueba3 = fechaReservaInicio.isAfter(fechaI) && fechaReservaInicio.isBefore(fechaF);
                boolean prueba4 = fechaReservaFin.isAfter(fechaI) && fechaReservaFin.isBefore(fechaF);

                if (prueba1 || prueba2 || prueba3 || prueba4) {
                    encontrado = true;
                }

                it.remove();
            }
            if(!encontrado)
                listaVehiculo.add(vehiculo);
        }
        return listaVehiculo;
    }

    public void updateList( String[] marca, VehiculoCiudad ciudad) {
        if(marca[0] == "" && marca[1] == "")
            gVehiculos.setItems(this.ChooseDate(service.buscarPorCiudad(ciudad), fechaInicio, fechaFin));
        else {
            if (marca[0] != "" && marca[1] == "")
                gVehiculos.setItems(this.ChooseDate(service.buscarPorMarcaYCiudad(marca[0], ciudad),fechaInicio,fechaFin));
            else {
                if(marca[0] == "" && marca[1] != "")
                    gVehiculos.setItems(this.ChooseDate(service.buscarPorModeloYCiudad(marca[1], ciudad), fechaInicio,fechaFin));
                else {
                    gVehiculos.setItems(this.ChooseDate(service.buscarPorMarcaYModeloYCiudad(marca[0],marca[1],ciudad),fechaInicio,fechaFin));
                }
            }
        }
    }

}

