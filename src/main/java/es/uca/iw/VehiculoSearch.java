package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.combobox.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static es.uca.iw.ElegirFecha.ChooseDate;

@Route(value="search", layout = MainView.class)
public class VehiculoSearch extends VerticalLayout {

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
        for(Vehiculo vehiculo : ChooseDate(reservaService, service.buscarPorCiudad(ciudad),fechaInicio,fechaFin))
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
            if (SecurityUtils.isUserLoggedIn()) {
                Reserva r = new Reserva();
                r.setFechaInicio(fechaInicio);
                r.setFechaFin(fechaFin);

                r.setVehiculo(gVehiculos.asSingleSelect().getValue());
                UI.getCurrent().getSession().setAttribute(Vehiculo.class, gVehiculos.asSingleSelect().getValue());

                Random random = new Random();
                Long num = Math.abs(random.nextLong());

                while(reservaService.listarPorCodigo(num) != null) {
                    num = Math.abs(random.nextLong());
                }

                Period p = Period.between(r.getFechaInicio(),r.getFechaFin());

                long dias = p.getDays();

                r.setPrecioTotal(gVehiculos.asSingleSelect().getValue().getPrecio() * ((dias) + (p.getMonths() * 30) + (p.getYears() *365)));

                r.setCodigo(num);
                r.setUsuario(UI.getCurrent().getSession().getAttribute(Usuario.class));
                UI.getCurrent().getSession().setAttribute(Reserva.class, r);
                UI.getCurrent().navigate("PagoView");
            } else {
                Notification.show("¡Debe estar registrado!");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    Notification.show("Ha ocurrido un error!");
                }
                UI.getCurrent().navigate("Login");
            }
        });

        info.addClickListener(event -> {
            UI.getCurrent().getSession().setAttribute(Long.class, gVehiculos.asSingleSelect().getValue().getId());
            UI.getCurrent().navigate("Info");
        });
    }

    public void updateList( String[] marca, VehiculoCiudad ciudad, Double precio) {
        if(marca[0] == "" && marca[1] == "" && precio == null)
            gVehiculos.setItems(ChooseDate(reservaService, service.buscarPorCiudad(ciudad), fechaInicio, fechaFin));
        else {
            if(!marca[0].equals("") && marca[1].equals("") && precio == null) {
                gVehiculos.setItems(ChooseDate(reservaService, service.buscarPorMarcaYCiudad(marca[0], ciudad), fechaInicio, fechaFin));
            } else {
                if(marca[0].equals("") && !marca[1].equals("") && precio == null) {
                    gVehiculos.setItems(ChooseDate(reservaService, service.buscarPorModeloYCiudad(marca[1], ciudad), fechaInicio, fechaFin));
                } else {
                    if (marca[0].equals("") && marca[1].equals("") && precio != null) {
                        gVehiculos.setItems(ChooseDate(reservaService, service.buscarPorPrecioYCiudad(precio,ciudad), fechaInicio, fechaFin));
                    } else {
                        if(!marca[0].equals("") && !marca[1].equals("") && precio == null) {
                            gVehiculos.setItems(ChooseDate(reservaService, service.buscarPorMarcaYModeloYCiudad(marca[0],marca[1],ciudad), fechaInicio, fechaFin));
                        } else {
                            if (!marca[0].equals("") && marca[1].equals("") && precio != null) {
                                gVehiculos.setItems(ChooseDate(reservaService, service.buscarPorMarcaYPrecioYCiudad(marca[0], precio, ciudad), fechaInicio,fechaFin));
                            } else {
                                if (marca[0].equals("") && !marca[1].equals("") && precio != null) {
                                    gVehiculos.setItems(ChooseDate(reservaService, service.buscarPorModeloYPrecioYCiudad(marca[1], precio, ciudad), fechaInicio, fechaFin));
                                } else {
                                    if(!marca[0].equals("") && !marca[1].equals("") && precio != null) {
                                        gVehiculos.setItems(ChooseDate(reservaService, service.buscarPorMarcaYModeloYPrecioCiudad(marca[0], marca[1], precio, ciudad), fechaInicio, fechaFin));
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

