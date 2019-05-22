package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
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

            //H1 contenido = new H1(reserva.getUsuario().getNombre());
            H1 contenido = new H1("Hola");
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
