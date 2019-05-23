package es.uca.iw;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route(value = "", layout = MainView.class)
public class FechaSelectView {
    private DatePicker fechaInicio;
    private DatePicker fechaFin;
    private FechaSelectForm form;

    public FechaSelectView() {
        fechaInicio = new DatePicker();
        fechaFin = new DatePicker();
        form = new FechaSelectForm();



        LocalDate now = LocalDate.now();

        fechaInicio.setMin(now);
    }
}
