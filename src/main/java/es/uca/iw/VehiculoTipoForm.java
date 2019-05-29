package es.uca.iw;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;


public class VehiculoTipoForm extends FormLayout {
    private TextField tipo = new TextField("Tipo");
    private ComboBox<VehiculoMarca> marca = new ComboBox<>("Marca");
    private Button save = new Button("Añadir");
    private Button delete = new Button("Borrar");
    private VehiculoTipoGestionView vehiculoTipoView;
    private Binder<VehiculoTipo> binder = new Binder<>(VehiculoTipo.class);
    private VehiculoTipoService serviceTipo;
    private VehiculoMarcaService vehiculoMarcaService;
    private ReservaService reservaService;
    private PagoService pagoService;
    private VehiculoService vehiculoService;

    public VehiculoTipoForm(VehiculoTipoGestionView vehiculoTipoView, VehiculoTipoService serviceTipo,
                            VehiculoMarcaService vehiculoMarcaService, ReservaService reservaService, PagoService pagoService, VehiculoService vehiculoService) {
        this.vehiculoTipoView = vehiculoTipoView;
        this.serviceTipo = serviceTipo;
        this.vehiculoMarcaService = vehiculoMarcaService;
        this.reservaService = reservaService;
        this.pagoService = pagoService;
        this.vehiculoService = vehiculoService;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(tipo, marca, buttons);
        tipo.setRequired(true);
        marca.setRequired(true);
        marca.setItems(vehiculoMarcaService.listarMarca());
        marca.setItemLabelGenerator(VehiculoMarca::getMarca);
        save.addClickShortcut(Key.ENTER);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> {
            if(binder.getBean() != null)
                save();});
        delete.addClickListener(event -> {
            if(binder.getBean() != null)
                delete();});
    }

    public void setTipo(VehiculoTipo tipo) {
        binder.setBean(tipo);

        if(tipo == null) {
            setVisible(false);
        }
        else {
            setVisible(true);
            this.tipo.focus();
        }
    }

    public void save() {
        VehiculoTipo tipo = binder.getBean();
        if(binder.validate().isOk() && !binder.getBean().getTipo().equals("") && binder.getBean().getMarca() != null) {
            serviceTipo.guardarTipo(tipo);
            this.vehiculoTipoView.updateList();
            setTipo(null);
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        VehiculoTipo tipo = binder.getBean();
        if(binder.validate().isOk() && !binder.getBean().getTipo().equals("") && binder.getBean().getMarca() != null) {
            for (Vehiculo v : vehiculoService.listarPorTipo(tipo)) {
                SustitucionVehiculo.sustituir(reservaService, v, vehiculoService, pagoService);
                vehiculoService.borrarVehiculo(v);
            }
            serviceTipo.borrarTipo(tipo);
            this.vehiculoTipoView.updateList();
            setTipo(null);
        } else
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);

    }
}
