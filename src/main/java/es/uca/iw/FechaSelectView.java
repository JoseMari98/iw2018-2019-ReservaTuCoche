package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.formlayout.FormLayout;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Route(value = "", layout = MainView.class)
public class FechaSelectView extends FormLayout {
    private DatePicker fechaInicio;
    private DatePicker fechaFin;
    private ComboBox<VehiculoCiudad> ciudad = new ComboBox<>();
    private Button envia = new Button("Elegir");

    public FechaSelectView() {
        fechaInicio = new DatePicker();
        fechaFin = new DatePicker();
        H1 titulo = new H1("Reserva tu Coche");
        Label aviso = new Label("*Debes estar registrado para buscar!");
        LocalDate now = LocalDate.now();
        fechaInicio.setMin(now);
        fechaInicio.setRequired(true);
        fechaFin.setRequired(true);
        ciudad.setItems(VehiculoCiudad.values());
        ciudad.setLabel("Ciudad");
        fechaInicio.setLabel("Fecha de inicio");
        fechaFin.setLabel("Fecha final");
        ciudad.setRequired(true);

        fechaFin.addValueChangeListener(e -> {
            if(fechaInicio.isEmpty()) {
                fechaFin.setMin(now);
            }else {
                fechaFin.setMin(fechaInicio.getValue());
            }
        });

        fechaInicio.addValueChangeListener(e -> {
            fechaInicio.setMax(fechaFin.getValue());

        });
        VerticalLayout layout = new VerticalLayout(titulo, aviso, fechaInicio, fechaFin, ciudad, envia);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        layout.setAlignItems(FlexComponent.Alignment.END);
        envia.addClickListener(e -> {
            if(fechaInicio.isInvalid() || fechaFin.isInvalid() || fechaInicio.isEmpty() || fechaFin.isEmpty()) {
                Notification.show("¡Algo anda mal con la fecha!");
                fechaInicio.clear();
                fechaFin.clear();
            } else {
                if(ciudad.isEmpty()) {
                    Notification.show("¡Debe introducir una ciudad!");
                } else{
                    if (!fechaInicio.isEmpty() && !fechaFin.isEmpty()) {
                        if(SecurityUtils.isUserLoggedIn()) {
                            Reserva r = new Reserva();
                            UI.getCurrent().getSession().setAttribute(VehiculoCiudad.class,ciudad.getValue());
                            r.setFechaInicio(fechaInicio.getValue());
                            r.setFechaFin(fechaFin.getValue());
                            UI.getCurrent().getSession().setAttribute(Reserva.class, r);
                            UI.getCurrent().navigate("search");
                        } else{
                            Notification.show("¡Debe estar registrado!");
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e1) {
                                Notification.show("Ha ocurrido un error!");
                            }
                            UI.getCurrent().navigate("UsuarioView");
                        }
                    }
                }
            }
        });
        add(layout);

    }
}
