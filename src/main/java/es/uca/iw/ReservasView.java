package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Route(value = "MisReserva", layout = MainView.class)
@Secured("User")
public class ReservasView extends AbstractView {
    private Grid<Reserva> grid = new Grid<>(Reserva.class);
    private ReservaService service;
    private Button delete = new Button("Borrar");
    private BeanValidationBinder<Reserva> binder = new BeanValidationBinder<>(Reserva.class);

    @Autowired
    public ReservasView(ReservaService service) {
        this.service = service;

        grid.setColumns("vehiculo.modelo.modelo", "vehiculo.marca.marca","fechaInicio","fechaFin","precioTotal");

        grid.setSizeFull();

        add(delete, grid);

        setSizeFull();

        updateList();

        grid.asSingleSelect().addValueChangeListener(event -> setReserva(grid.asSingleSelect().getValue()));

        delete.addClickListener(event -> delete());
    }

    public void updateList() {
        grid.setItems(service.findAll());
    } //cambiar para que solo se muestre

    public void setReserva(Reserva reserva) {
        binder.setBean(reserva);
    }

    public void delete() {
        Reserva reserva = binder.getBean();
        service.delete(reserva);
        updateList();
    }
}