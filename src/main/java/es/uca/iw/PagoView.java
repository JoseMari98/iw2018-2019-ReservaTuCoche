package es.uca.iw;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("PagoView")
public class PagoView extends VerticalLayout {
    private PagoService pagoService;
    private PagoForm form;

    @Autowired
    public PagoView(PagoService pagoService/*, Reserva reserva*/) {
        this.pagoService = pagoService;
        this.form = new PagoForm(this, pagoService);

        //H1 contenido = new H1(reserva.getUsuario().getNombre());
        VerticalLayout mainContent = new VerticalLayout(/*contenido,*/ form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();

        add(mainContent);

        setSizeFull();
    }
}
