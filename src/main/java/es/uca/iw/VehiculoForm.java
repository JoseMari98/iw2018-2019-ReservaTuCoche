package es.uca.iw;

import com.vaadin.flow.component.Key;
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
import java.util.List;

public class VehiculoForm extends FormLayout {
    private TextField matricula = new TextField("Matricula");
    private ComboBox<VehiculoCiudad> ciudad = new ComboBox<>("Ciudad");
    private ComboBox<VehiculoMarca> marca = new ComboBox<>("Marca");
    private ComboBox<VehiculoModelo> modelo = new ComboBox<>("Modelo");
    private ComboBox<VehiculoTipo> tipo = new ComboBox<>("Tipo");
    private NumberField precio = new NumberField("Precio");
    private NumberField plazas = new NumberField("Plazas");
    private NumberField puertas = new NumberField("Puertas");
    private ComboBox<VehiculoMotor> motor = new ComboBox<>("Motor");
    private ComboBox<VehiculoAC> ac = new ComboBox<>("A/C");
    private Button save = new Button("Añadir");
    private Button delete = new Button("Borrar");
    private VehiculoGestionView vehiculoView;
    private BeanValidationBinder<Vehiculo> binder = new BeanValidationBinder<>(Vehiculo.class);
    private VehiculoService serviceVehiculo;
    private VehiculoMarcaService serviceMarca;
    private VehiculoModeloService serviceModelo;
    private VehiculoTipoService serviceTipo;
    private ReservaService reservaService;
    private PagoService pagoService;

    public VehiculoForm(VehiculoGestionView vehiculoView, VehiculoService service, VehiculoMarcaService serviceMarca, VehiculoModeloService serviceModelo,
                        VehiculoTipoService serviceTipo, ReservaService reservaService, PagoService pagoService) {
        this.serviceVehiculo = service;
        this.pagoService = pagoService;
        this.reservaService = reservaService;
        this.vehiculoView = vehiculoView;
        this.serviceMarca = serviceMarca;
        this.serviceModelo = serviceModelo;
        this.serviceTipo = serviceTipo;
        precio.setSuffixComponent(new Span("€"));
        precio.setMin(0);
        precio.setMax(10000);
        puertas.setMin(0);
        puertas.setMax(5);
        plazas.setMin(0);
        plazas.setMax(7);
        precio.setStep(0.01);
        matricula.setRequired(true);
        tipo.setRequired(true);
        marca.setRequired(true);
        modelo.setRequired(true);
        ciudad.setRequired(true);
        plazas.setRequiredIndicatorVisible(true);
        puertas.setRequiredIndicatorVisible(true);
        ac.setRequired(true);
        motor.setRequired(true);
        marca.addValueChangeListener(e -> {
            tipo.setItems(serviceTipo.listarPorMarca(marca.getValue()));
        } );
        marca.setItemLabelGenerator(VehiculoMarca::getMarca);
        modelo.setItemLabelGenerator(VehiculoModelo::getModelo);
        tipo.setItemLabelGenerator(VehiculoTipo::getTipo);
        tipo.setItems(serviceTipo.listarTipo());
        marca.setItems(serviceMarca.listarMarca());
        modelo.setItems(serviceModelo.listarModelo());
        ac.setItems(VehiculoAC.values());
        motor.setItems(VehiculoMotor.values());
        ciudad.setItems(VehiculoCiudad.values());
        save.addClickShortcut(Key.ENTER);


        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(matricula, marca, modelo, tipo, precio, puertas, plazas, motor, ac, ciudad, buttons);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> {
            if(binder.getBean() != null)
                save();});
        delete.addClickListener(event -> {
            if(binder.getBean() != null)
                delete();});
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
        if(binder.validate().isOk()) {
            SustitucionVehiculo.sustituir(reservaService, vehiculo, serviceVehiculo, pagoService);
            serviceVehiculo.borrarVehiculo(vehiculo);
            this.vehiculoView.updateList();
            setVehiculo(null);
        } else
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
    }
}
