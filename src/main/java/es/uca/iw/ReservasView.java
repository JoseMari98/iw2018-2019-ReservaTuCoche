package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import java.time.LocalDate;


@Route(value = "MisReservas", layout = MainView.class)
@Secured("User")
public class ReservasView extends AbstractView {
    private Grid<Reserva> grid = new Grid<>(Reserva.class);
    private ReservaService service;
    private Button delete = new Button("Borrar");
    private BeanValidationBinder<Reserva> binder = new BeanValidationBinder<>(Reserva.class);
    private UsuarioService usuarioService;
    private PagoService pagoService;

    @Autowired
    public ReservasView(ReservaService service, UsuarioService usuarioService, PagoService pagoService) {
        this.service = service;
        this.usuarioService = usuarioService;
        this.pagoService = pagoService;

        grid.setColumns("codigo", "vehiculo.modelo.modelo", "vehiculo.marca.marca","fechaInicio","fechaFin","precioTotal");

        grid.setSizeFull();

        add(delete, grid);

        setSizeFull();

        updateList();

        grid.asSingleSelect().addValueChangeListener(event -> setReserva(grid.asSingleSelect().getValue()));

        Dialog dialog = new Dialog();

        Label label = new Label("Si borras la reserva solo se devolvera la fianza");

        Button confirmButton = new Button("Confirmar", event -> {
            if(binder.getBean().getFechaFin().isBefore(LocalDate.now()))
                Notification.show("No puedes borrar una reserva que ya ha pasado");
            else {
                delete();
            }
            dialog.close();
        });
        confirmButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        Button cancelButton = new Button("Cancelar", event -> {
            dialog.close();
        });

        dialog.add(label, confirmButton, cancelButton);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        delete.addClickListener(e -> dialog.open());
    }

    public void updateList() {
        Usuario u = UI.getCurrent().getSession().getAttribute(Usuario.class);
        grid.setItems(service.listarPorUsuario(u));
    }

    public void setReserva(Reserva reserva) {
        binder.setBean(reserva);
    }

    public void delete() {
        Reserva reserva = binder.getBean();
        DevolverFianza.devolver(pagoService, reserva);
        service.delete(reserva);
        updateList();
    }
}