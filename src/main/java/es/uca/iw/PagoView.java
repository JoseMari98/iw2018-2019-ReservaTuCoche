package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Route(value = "PagoView", layout = MainView.class)
@Secured("User")
public class PagoView extends AbstractView {
    private PagoService pagoService;
    private TarjetaCreditoService tarjetaService;
    private PagoForm form;

    @Autowired
    public PagoView(PagoService pagoService, TarjetaCreditoService tarjetaService) {
        if(UI.getCurrent().getSession().getAttribute(Reserva.class) != null) {
            this.pagoService = pagoService;
            this.tarjetaService = tarjetaService;
            this.form = new PagoForm(this, pagoService, tarjetaService);

            HorizontalLayout contenido = new HorizontalLayout();
            H1 titulo = new H1("Datos de la reserva");
            H2 fecha = new H2("Para la fecha " +
                    UI.getCurrent().getSession().getAttribute(Reserva.class).getFechaInicio().toString() +
                    " hasta la fecha " + UI.getCurrent().getSession().getAttribute(Reserva.class).getFechaFin().toString());
            H2 vehiculo = new H2("El vehiculo " + UI.getCurrent().getSession().getAttribute(Vehiculo.class).getMarca() +
                    " " + UI.getCurrent().getSession().getAttribute(Vehiculo.class).getTipo());
            contenido.add(titulo, fecha, vehiculo);
            VerticalLayout mainContent = new VerticalLayout(contenido, form); //metemos en un objeto el grid y formulario
            mainContent.setSizeFull();

            add(mainContent);

            setSizeFull();
        } else {
            UI.getCurrent().navigate("");
            UI.getCurrent().getPage().reload();
        }
    }
}
