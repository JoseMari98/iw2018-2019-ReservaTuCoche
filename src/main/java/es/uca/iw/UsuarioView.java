package es.uca.iw;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("RegistroUsuario")

public class UsuarioView extends VerticalLayout {

    private Grid<Usuario> grid = new Grid<>(Usuario.class);
    private UsuarioForm form;
    private TextField filterText = new TextField();
    private UsuarioService service;

    @Autowired
    UsuarioView(UsuarioService service){
        this.service = service;
        this.form = new UsuarioForm( this , service);

        add(form);
        setSizeFull();

    }
}
