package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("cocheform")
public class CocheForm extends FormLayout {
    private TextField matricula = new TextField("Matricula");
    private ComboBox<CocheMarca> marca = new ComboBox<>("Marca");
    private ComboBox<CocheModelo> modelo = new ComboBox<>("Modelo");
    private NumberField precio = new NumberField("Precio");
    private Button save = new Button("Save");
    //private Button delete = new Button("Delete");

    public CocheForm() {
        marca.setItems(CocheMarca.values());
        modelo.setItems(CocheModelo.values());

        HorizontalLayout buttons = new HorizontalLayout(save);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(matricula, marca, modelo, precio, buttons);
    }
}
