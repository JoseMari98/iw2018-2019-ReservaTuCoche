package es.uca.iw;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Route(value = "GestionReservas", layout = MainView.class)
@Secured({"Admin", "Gerente"})
public class ReservaGestionView extends AbstractView {
    private Grid<Reserva> grid = new Grid<>(Reserva.class);
    private TextField filterText = new TextField();
    private ReservaService service;
    private Button buscar = new Button("Buscar");
    private Button delete = new Button("Borrar");
    private Binder<Reserva> binder = new Binder<>(Reserva.class);
    private PagoService pagoService;
    private UsuarioService usuarioService;

    @Autowired
    public ReservaGestionView(ReservaService service, PagoService pagoService, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;

        filterText.setPlaceholder("Filtrar por nombre de usuario"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        buscar.addClickListener(event -> {
            Usuario usuario = usuarioService.listarPorUsername(filterText.getValue());
            if(usuario != null && !service.listarPorUsuario(usuario).isEmpty())
                updateList();
            else {
                filterText.clear();
                Notification.show("No hay ningun vehiculo con esa matricula", 2000, Notification.Position.MIDDLE);
            }
        });
        filterText.addValueChangeListener(e -> {
            if(filterText.isEmpty())
                updateList();
        });
        buscar.addClickShortcut(Key.ENTER);
        HorizontalLayout toolbar = new HorizontalLayout(filterText, buscar, delete);

        grid.setColumns("codigo", "vehiculo.matricula", "usuario.username","fechaInicio","fechaFin","precioTotal");

        grid.setSizeFull();

        add(toolbar, grid);

        setSizeFull();

        updateList();

        grid.asSingleSelect().addValueChangeListener(event -> setReserva(grid.asSingleSelect().getValue()));

        delete.addClickListener(event -> {
            if(binder.getBean() != null)
                delete();});    }

    public void updateList() {
        if(filterText.isEmpty())
            grid.setItems(service.findAll());
        else
            grid.setItems(service.listarPorUsuario(usuarioService.listarPorUsername(filterText.getValue())));

    }

    public void setReserva(Reserva reserva) {
        binder.setBean(reserva);
    }

    public void delete() {
        Reserva reserva = binder.getBean();
        if(binder.validate().isOk()) {
            DevolverFianza.devolver(pagoService, reserva);
            service.delete(reserva);
            updateList();
        }
        else
            Notification.show("Borrado invalido", 5000, Notification.Position.MIDDLE);
    }
}
