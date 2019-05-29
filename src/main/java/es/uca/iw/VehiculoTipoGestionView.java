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

@Route(value = "GestionTipo", layout = MainView.class)
@Secured({"Admin", "Gerente"})
public class VehiculoTipoGestionView extends AbstractView {
    private Grid<VehiculoTipo> grid = new Grid<>(VehiculoTipo.class);
    private TextField filterText = new TextField();
    private VehiculoTipoService serviceTipo;
    private VehiculoTipoForm form;

    @Autowired
    public VehiculoTipoGestionView(VehiculoTipoService serviceTipo, VehiculoMarcaService vehiculoMarcaService, ReservaService reservaService, PagoService pagoService, VehiculoService vehiculoService) {
        this.serviceTipo = serviceTipo;
        this.form = new VehiculoTipoForm(this, serviceTipo, vehiculoMarcaService, reservaService, pagoService, vehiculoService);

        filterText.setPlaceholder("Filtrar por tipo"); //poner el campo
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
        Button addModeloBtn = new Button ("Añade un tipo");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setTipo(new VehiculoTipo()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addModeloBtn);

        grid.setColumns("tipo", "marca.marca");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setTipo(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setTipo(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if(filterText.isEmpty())
            grid.setItems(serviceTipo.listarTipo());
        else
            grid.setItems(serviceTipo.listarTipoPorTipo(filterText.getValue()));
    }
}