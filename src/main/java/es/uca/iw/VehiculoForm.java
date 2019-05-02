package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;


public class VehiculoForm extends FormLayout {
    private TextField matricula = new TextField("Matricula");
    private ComboBox<VehiculoMarca> marca = new ComboBox<>("Marca");
    private ComboBox<VehiculoModelo> modelo = new ComboBox<>("Modelo");
    private NumberField precio = new NumberField("Precio");
    private Button save = new Button("Añadir");
    private Button delete = new Button("Borrar");
    private VehiculoGestionView vehiculoView;
    private BeanValidationBinder<Vehiculo> binder = new BeanValidationBinder<>(Vehiculo.class);
    private VehiculoService serviceVehiculo;
    private VehiculoMarcaService serviceMarca;
    private VehiculoModeloService serviceModelo;


    public VehiculoForm(VehiculoGestionView vehiculoView, VehiculoService service, VehiculoMarcaService serviceMarca, VehiculoModeloService serviceModelo) {
        this.serviceVehiculo = service;
        this.vehiculoView = vehiculoView;
        this.serviceMarca = serviceMarca;
        this.serviceModelo = serviceModelo;

        precio.setSuffixComponent(new Span("€"));
        precio.setStep(0.01);
        matricula.setRequired(true);

        marca.setRequired(true);
        modelo.setRequired(true);
        marca.setItemLabelGenerator(VehiculoMarca::getMarca);
        modelo.setItemLabelGenerator(VehiculoModelo::getModelo);
        marca.setItems(serviceMarca.listarMarca());
        modelo.setItems(serviceModelo.listarModelo());

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(matricula, marca, modelo, precio, buttons);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setVehiculo(Vehiculo vehiculo) {
        binder.setBean(vehiculo);

        if(vehiculo == null) {
            setVisible(false);
        }
        else {
            setVisible(true);
            matricula.focus();
        }
    }

    public void save() {
        Vehiculo vehiculo = binder.getBean();
        if(binder.validate().isOk()) {
            serviceVehiculo.guardarVehiculo(vehiculo);
            this.vehiculoView.updateList();
            setVehiculo(null);
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        Vehiculo vehiculo = binder.getBean();
        serviceVehiculo.borrarVehiculo(vehiculo);
        this.vehiculoView.updateList();
        setVehiculo(null);
    }
}
