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

@Route(value = "GestionMarca", layout = MainView.class)
@Secured({"Admin","Gerente"})
public class VehiculoMarcaGestionView extends AbstractView {
    private Grid<VehiculoMarca> grid = new Grid<>(VehiculoMarca.class);
    private TextField filterText = new TextField();
    private VehiculoMarcaService serviceMarca;
    private VehiculoMarcaForm form;

    @Autowired
    public VehiculoMarcaGestionView(VehiculoMarcaService serviceMarca, VehiculoTipoService tipoService, VehiculoService vehiculoService, PagoService pagoService, ReservaService reservaService) {
        this.serviceMarca = serviceMarca;
        this.form = new VehiculoMarcaForm(this, serviceMarca, tipoService, vehiculoService, pagoService, reservaService);

        filterText.setPlaceholder("Filtrar por nombre de marca"); //poner el campo
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
        Button addMarcaBtn = new Button ("Añade una marca");
        addMarcaBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setMarca(new VehiculoMarca()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addMarcaBtn);

        grid.setColumns("marca");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setMarca(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setMarca(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if(filterText.isEmpty())
            grid.setItems(serviceMarca.listarMarca());
        else
            grid.setItems(serviceMarca.listarMarcaPorMarca(filterText.getValue()));
    }
}
