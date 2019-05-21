package es.uca.iw;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "RegistroUsuario", layout = MainView.class)
public class UsuarioView extends VerticalLayout {

    private UsuarioForm form;
    private UsuarioService service;

    @Autowired
    UsuarioView(UsuarioService service){
        this.service = service;
        this.form = new UsuarioForm( this , service);
        H1 titulo = new H1("Registro");
        VerticalLayout contenido = new VerticalLayout(titulo, form);
        contenido.setSizeFull();
        add(contenido);
        setSizeFull();

    }
}
