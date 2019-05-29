package es.uca.iw;

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

@Route(value = "GestionReservas", layout = MainView.class)
@Secured({"Admin", "Gerente"})
public class ReservaGestionView extends AbstractView {
    private Grid<Reserva> grid = new Grid<>(Reserva.class);
    private TextField filterText = new TextField();
    private ReservaService service;
    private Button delete = new Button("Borrar");
    private Binder<Reserva> binder = new Binder<>(Reserva.class);
    private PagoService pagoService;

    @Autowired
    public ReservaGestionView(ReservaService service, PagoService pagoService) {
        this.service = service;

        filterText.setPlaceholder("Filtrar por nombre de marca"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filterText,delete);

        grid.setColumns("vehiculo.matricula", "usuario.username","fechaInicio","fechaFin","precioTotal");

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
            grid.setItems(service.listarReservaPorMatricula(filterText.getValue()));

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
