package es.uca.iw;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Route("PagoView")
@Secured("User")
public class PagoView extends AbstractView {
    private PagoService pagoService;
    private PagoForm form;

    @Autowired
    public PagoView(PagoService pagoService) {
        this.pagoService = pagoService;
        this.form = new PagoForm(this, pagoService);

        //H1 contenido = new H1(reserva.getUsuario().getNombre());
        H1 contenido = new H1("Hola");
        VerticalLayout mainContent = new VerticalLayout(contenido, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();

        add(mainContent);

        setSizeFull();
    }
}
