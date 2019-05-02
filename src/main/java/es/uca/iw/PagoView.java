package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("PagoView")
public class PagoView extends VerticalLayout {
    private Grid<Vehiculo> grid = new Grid<>(Vehiculo.class);
    private TextField filterText = new TextField();
    private VehiculoService service;
    private VehiculoForm form;

    @Autowired
    public VehiculoGestionView(VehiculoService service, VehiculoMarcaService serviceMarca, VehiculoModeloService serviceModelo) {
        this.service = service;
        this.form = new VehiculoForm(this, service, serviceMarca, serviceModelo);

        filterText.setPlaceholder("Filtrar por matricula"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> updateList());

        Button addVehiculoBtn = new Button ("Añade un vehículo");
        addVehiculoBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setVehiculo(new Vehiculo()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addVehiculoBtn);

        grid.setColumns("matricula", "modelo.modelo","marca.marca","precio");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setVehiculo(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setVehiculo(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if(filterText.isEmpty())
            grid.setItems(service.listarVehiculo());
        else
            grid.setItems(service.listarVehiculoPorMatricula(filterText.getValue()));
    }
}
