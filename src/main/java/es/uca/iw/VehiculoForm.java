package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;


public class VehiculoForm extends FormLayout {
    private TextField matricula = new TextField("Matricula");
    private ComboBox<VehiculoMarca> marca = new ComboBox<>("Marca");
    private ComboBox<VehiculoModelo> modelo = new ComboBox<>("Modelo");
    private NumberField precio = new NumberField("Precio");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private VehiculoGestionView vehiculoView;

    public VehiculoForm(VehiculoGestionView vehiculoView) {
        this.vehiculoView = vehiculoView;
        marca.setItems(VehiculoMarca.values());
        modelo.setItems(VehiculoModelo.values()); hay que sacar los valores de la base de datos

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(matricula, marca, modelo, precio, buttons);
    }
}
