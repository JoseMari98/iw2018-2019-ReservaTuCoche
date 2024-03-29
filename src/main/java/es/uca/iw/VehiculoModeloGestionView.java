package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Route(value = "GestionModelo", layout = MainView.class)
@Secured({"Admin", "Gerente"})
public class VehiculoModeloGestionView extends AbstractView {
    private Grid<VehiculoModelo> grid = new Grid<>(VehiculoModelo.class);
    private TextField filterText = new TextField();
    private VehiculoModeloService serviceModelo;
    private VehiculoModeloForm form;

    @Autowired
    public VehiculoModeloGestionView(VehiculoModeloService serviceModelo, VehiculoService vehiculoService, PagoService pagoService, ReservaService reservaService) {
        this.serviceModelo = serviceModelo;
        this.form = new VehiculoModeloForm(this, serviceModelo, vehiculoService, pagoService, reservaService);

        filterText.setPlaceholder("Filtrar por modelo"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> {
            if(vehiculoService.listarVehiculoPorMatricula(filterText.getValue()) != null)
                updateList();
            else {
                filterText.clear();
                Notification.show("No hay ningun vehiculo con esa matricula", 2000, Notification.Position.MIDDLE);
            }

        });
        Button addModeloBtn = new Button ("Añade un modelo");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setModelo(new VehiculoModelo()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addModeloBtn);

        grid.setColumns("modelo");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setModelo(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setModelo(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if(filterText.isEmpty())
            grid.setItems(serviceModelo.listarModelo());
        else
            grid.setItems(serviceModelo.listarModeloPorModelo(filterText.getValue()));
    }
}
