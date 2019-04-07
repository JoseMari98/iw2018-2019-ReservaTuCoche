package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("coche/form")
public class VehiculoGestionView extends VerticalLayout {
    private Grid<Vehiculo> grid = new Grid<>(Vehiculo.class);
    private TextField filterText = new TextField();
    private VehiculoForm form = new VehiculoForm();

    public VehiculoGestionView() {
        filterText.setPlaceholder("Filter by name...?"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba

        Button addCustomerBtn = new Button ("Add new customer");
        /*addCustomerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setCustomer(new Customer()); //instancia un nuevo customer
        });*/

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addCustomerBtn);

        grid.setColumns("matricula","modelo","marca","precio");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();
    }
}
