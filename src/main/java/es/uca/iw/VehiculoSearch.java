package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.combobox.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Stream;

@Route(value="search", layout = MainView.class)
@Secured("User")
public class VehiculoSearch extends AbstractView {

    private Grid<Vehiculo> gVehiculos = new Grid<>(Vehiculo.class);
    private VehiculoService service;
    private VehiculoMarcaService serviceMarca;
    private VehiculoModeloService serviceModelo;
    private Button reserva = new Button("Reservar");
    private Button info = new Button("Mas información");
    private ComboBox<VehiculoMarca> buscaMarca = new ComboBox<>();
    private ComboBox<VehiculoModelo> buscaModelo = new ComboBox<>();
    private NumberField precioMax = new NumberField("Precio máximo");
    private ReservaService reservaService;

    private LocalDate fechaFin;
    private LocalDate fechaInicio;

    @Autowired
    public VehiculoSearch(VehiculoService serv, VehiculoMarcaService serviceMarca, VehiculoModeloService serviceModelo, ReservaService reservaService) {
        service = serv;
        this.serviceMarca = serviceMarca;
        this.serviceModelo = serviceModelo;
        this.reservaService = reservaService;
        buscaMarca.setLabel("Marca");
        buscaModelo.setLabel("Modelo");
        buscaMarca.setItemLabelGenerator(VehiculoMarca::getMarca);
        buscaModelo.setItemLabelGenerator(VehiculoModelo::getModelo);

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

        buscaMarca.addValueChangeListener(event -> {
            if(buscaMarca.isEmpty()) {
                query[0] = "";
                updateList(query, ciudad, precioMax.getValue());
            } else {
                query[0] = buscaMarca.getValue().getMarca();
                updateList(query, ciudad, precioMax.getValue());
            }
        });

        buscaModelo.setItems(listaModelo);

        buscaModelo.addValueChangeListener( event -> {
            if (buscaModelo.isEmpty()) {
                query[1] = "";
                updateList(query,ciudad, precioMax.getValue());
            } else {
                query[1] = buscaModelo.getValue().getModelo();
                updateList(query,ciudad, precioMax.getValue());
            }
        });

        precioMax.addValueChangeListener(e -> {
            updateList(query, ciudad, precioMax.getValue());
        });

        gVehiculos.setColumns("marca.marca", "modelo.modelo", "precio");

        gVehiculos.setItems(listaVehiculo);

        HorizontalLayout filters = new HorizontalLayout(buscaMarca, buscaModelo, precioMax);

        HorizontalLayout botones = new HorizontalLayout(reserva,info);

        VerticalLayout reservar = new VerticalLayout(gVehiculos, botones);

        VerticalLayout lista = new VerticalLayout(filters, reservar);

        add(lista);

        setSizeFull();

        reserva.addClickListener(event -> {
            Reserva r = new Reserva();
            r.setFechaInicio(fechaInicio);
            r.setFechaFin(fechaFin);

            r.setVehiculo(gVehiculos.asSingleSelect().getValue());
            UI.getCurrent().getSession().setAttribute(Vehiculo.class, gVehiculos.asSingleSelect().getValue());

            Random random = new Random();
            Math.abs(random.nextLong());

            if(reservaService.listarPorCodigo(Math.abs(random.nextLong())) != null) {
                Math.abs(random.nextLong());
            }

            Period p = Period.between(r.getFechaInicio(),r.getFechaFin());

            long dias = p.getDays();

            r.setPrecioTotal(gVehiculos.asSingleSelect().getValue().getPrecio() * dias);

            r.setCodigo(random.nextLong());
            r.setUsuario(UI.getCurrent().getSession().getAttribute(Usuario.class));
            UI.getCurrent().getSession().setAttribute(Reserva.class, r);
            UI.getCurrent().navigate("PagoView");
        });

        info.addClickListener(event -> {
            UI.getCurrent().getSession().setAttribute(Long.class, gVehiculos.asSingleSelect().getValue().getId());
            UI.getCurrent().navigate("Info");
        });
    }

    public List<Vehiculo> ChooseDate(List<Vehiculo> list, LocalDate fechaI, LocalDate fechaF) {
        ArrayList<Vehiculo> listaVehiculo = new ArrayList<>();
        boolean encontrado = false;
        for (Vehiculo vehiculo : list) {
            List<Reserva> reservas = reservaService.listarReservaPorVehiculo(vehiculo);
            Iterator<Reserva> it = reservas.iterator();
            while(it.hasNext() && !encontrado) {
                Reserva r = it.next();
                LocalDate fechaReservaInicio = r.getFechaInicio();
                LocalDate fechaReservaFin = r.getFechaFin();

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

    public void updateList( String[] marca, VehiculoCiudad ciudad, Double precio) {
        if(marca[0] == "" && marca[1] == "" && precio == null)
            gVehiculos.setItems(this.ChooseDate(service.buscarPorCiudad(ciudad), fechaInicio, fechaFin));
        else {
            if(!marca[0].equals("") && marca[1].equals("") && precio == null) {
                gVehiculos.setItems(this.ChooseDate(service.buscarPorMarcaYCiudad(marca[0], ciudad), fechaInicio, fechaFin));
            } else {
                if(marca[0].equals("") && !marca[1].equals("") && precio == null) {
                    gVehiculos.setItems(this.ChooseDate(service.buscarPorModeloYCiudad(marca[1], ciudad), fechaInicio, fechaFin));
                } else {
                    if (marca[0].equals("") && marca[1].equals("") && precio != null) {
                        gVehiculos.setItems(this.ChooseDate(service.buscarPorPrecioYCiudad(precio,ciudad), fechaInicio, fechaFin));
                    } else {
                        if(!marca[0].equals("") && !marca[1].equals("") && precio == null) {
                            gVehiculos.setItems(this.ChooseDate(service.buscarPorMarcaYModeloYCiudad(marca[0],marca[1],ciudad), fechaInicio, fechaFin));
                        } else {
                            if (!marca[0].equals("") && marca[1].equals("") && precio != null) {
                                gVehiculos.setItems(this.ChooseDate(service.buscarPorMarcaYPrecioYCiudad(marca[0], precio, ciudad), fechaInicio,fechaFin));
                            } else {
                                if (marca[0].equals("") && !marca[1].equals("") && precio != null) {
                                    gVehiculos.setItems(this.ChooseDate(service.buscarPorModeloYPrecioYCiudad(marca[1], precio, ciudad), fechaInicio, fechaFin));
                                } else {
                                    if(!marca[0].equals("") && !marca[1].equals("") && precio != null) {
                                        gVehiculos.setItems(this.ChooseDate(service.buscarPorMarcaYModeloYPrecioCiudad(marca[0], marca[1], precio, ciudad), fechaInicio, fechaFin));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

