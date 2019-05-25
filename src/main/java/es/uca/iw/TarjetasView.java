package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Route(value = "MisTarjetas", layout = MainView.class)
@Secured("User")
public class TarjetasView extends AbstractView {
    private Grid<TarjetaCredito> grid = new Grid<>(TarjetaCredito.class);
    private TarjetaCreditoService service;
    private Button delete = new Button("Borrar");
    private BeanValidationBinder<TarjetaCredito> binder = new BeanValidationBinder<>(TarjetaCredito.class);
    private UsuarioService usuarioService;

    @Autowired
    public TarjetasView(TarjetaCreditoService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;

        grid.setColumns("numeroTarjeta", "numeroSeguridad","fechaCaducidad");

        grid.setSizeFull();

        add(delete, grid);

        setSizeFull();

        updateList();

        grid.asSingleSelect().addValueChangeListener(event -> setTarjeta(grid.asSingleSelect().getValue()));

        delete.addClickListener(event -> delete());
    }

    public void updateList() {
        Usuario u = UI.getCurrent().getSession().getAttribute(Usuario.class);
        grid.setItems(service.listarPorUsuario(u));
    }

    public void setTarjeta(TarjetaCredito tarjeta) {
        binder.setBean(tarjeta);
    }

    public void delete() {
        TarjetaCredito tarjetaCredito = binder.getBean();
        service.borrarTarjeta(tarjetaCredito);
        updateList();
    }
}