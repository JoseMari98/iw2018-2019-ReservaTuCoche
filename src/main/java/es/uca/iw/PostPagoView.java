package es.uca.iw;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Route( value = "GestionPostpago", layout = MainView.class)
@Secured("Admin")
public class PostPagoView extends AbstractView{

    private PostPagoForm form;
    private PagoService pagoService;
    private ReservaService reservaService;

    @Autowired
    PostPagoView(PagoService pagoService, ReservaService reservaService){
        this.pagoService = pagoService;
        this.reservaService = reservaService;

        this.form = new PostPagoForm(pagoService, reservaService);
        H2 titulo = new H2("Postpago");
        VerticalLayout contenido = new VerticalLayout(titulo, form);
        contenido.setSizeFull();
        add(contenido);
        setSizeFull();
    }
}