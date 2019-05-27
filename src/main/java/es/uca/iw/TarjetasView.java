package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

@Route(value = "MisTarjetas", layout = MainView.class)
@Secured("User")
public class TarjetasView extends AbstractView {
    private Grid<TarjetaCredito> grid = new Grid<>(TarjetaCredito.class);
    private TarjetaCreditoService service;
    private Button delete = new Button("Borrar");
    private BeanValidationBinder<TarjetaCredito> binder = new BeanValidationBinder<>(TarjetaCredito.class);
    private UsuarioService usuarioService;
    private ReservaService reservaService;

    @Autowired
    public TarjetasView(TarjetaCreditoService service, UsuarioService usuarioService, ReservaService reservaService) {
        if(UI.getCurrent().getSession().getAttribute(Usuario.class) != null) {
            this.service = service;
            this.usuarioService = usuarioService;
            this.reservaService = reservaService;

            grid.setColumns("numeroTarjeta", "numeroSeguridad", "fechaCaducidad");

            grid.setSizeFull();

            add(delete, grid);

            setSizeFull();

            updateList();

            grid.asSingleSelect().addValueChangeListener(event -> setTarjeta(grid.asSingleSelect().getValue()));

            Dialog dialog = new Dialog();

            Button confirmButton = new Button("Confirmar", event -> {
                boolean valido = true;
                List<Reserva> reservas = reservaService.listarPorUsuario(UI.getCurrent().getSession().getAttribute(Usuario.class));
                int i = 0;
                while (i < reservas.size() && valido) {
                    if (reservas.get(i).getReservaEstado() == ReservaEstado.Pendiente)
                        valido = false;
                    i++;
                }
                if (valido)
                    delete();
                else
                    Notification.show("No puedes borrar la tarjeta porque hay un pago pendiente", 4000, Notification.Position.MIDDLE);
                dialog.close();
            });
            confirmButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
            Button cancelButton = new Button("Cancelar", event -> {
                dialog.close();
            });
            dialog.add(confirmButton, cancelButton);
            cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

            delete.addClickListener(event -> dialog.open());
        } else
            UI.getCurrent().navigate("");
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