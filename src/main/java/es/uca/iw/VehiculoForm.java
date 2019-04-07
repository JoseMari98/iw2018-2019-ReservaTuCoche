package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import java.util.ArrayList;


public class VehiculoForm extends FormLayout {
    private TextField matricula = new TextField("Matricula");
    private ComboBox<VehiculoMarca> marca = new ComboBox<>("Marca");
    private ComboBox<VehiculoModelo> modelo = new ComboBox<>("Modelo");
    private NumberField precio = new NumberField("Precio");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private VehiculoGestionView vehiculoView;
    private Binder<Vehiculo> binderVehiculo = new Binder<>(Vehiculo.class);
    private Binder<VehiculoMarca> binderMarca = new Binder<>(VehiculoMarca.class);
    private Binder<VehiculoModelo> binderModelo = new Binder<>(VehiculoModelo.class);
    private VehiculoService service;


    public VehiculoForm(/*VehiculoGestionView vehiculoView*/) {
        //this.vehiculoView = vehiculoView;
        //Esto sirve para poder tener todas las marcas dentro de una lista
        ArrayList<VehiculoMarca> listaMarca = new ArrayList<>();
        for(VehiculoMarca marca : service.listarMarca())
            listaMarca.add(marca);
        marca.setItems(listaMarca);
        //Para tener una lista de modelos
        ArrayList<VehiculoModelo> listaModelo = new ArrayList<>();
        for(VehiculoModelo modelo : service.listarModelo())
            listaModelo.add(modelo);
        modelo.setItems(listaModelo);

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(matricula, marca, modelo, precio, buttons);
    }
}
