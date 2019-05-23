package es.uca.iw;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.formlayout.FormLayout;

import java.time.LocalDate;

@Route(value = "vista", layout = MainView.class)
public class FechaSelectView extends VerticalLayout {
    private DatePicker fechaInicio;
    private DatePicker fechaFin;
    private FechaSelectForm form;
    private ComboBox<VehiculoCiudad> ciudad = new ComboBox<>();

    public FechaSelectView() {
        fechaInicio = new DatePicker();
        fechaFin = new DatePicker();
        form = new FechaSelectForm(this);



        LocalDate now = LocalDate.now();

        fechaInicio.setMin(now);
        fechaInicio.setRequired(true);
        fechaFin.setRequired(true);
        ciudad.setItems(VehiculoCiudad.values());
        ciudad.setRequired(true);
        HorizontalLayout layout = new HorizontalLayout(fechaInicio, fechaFin, ciudad);

        form.setReserva(ciudad.getValue(), fechaInicio.getValue(), fechaFin.getValue());

        add(layout, form);
    }
}
