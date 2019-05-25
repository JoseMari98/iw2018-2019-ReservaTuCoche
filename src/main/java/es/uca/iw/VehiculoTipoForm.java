package es.uca.iw;

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

    public VehiculoTipoForm(VehiculoTipoGestionView vehiculoTipoView, VehiculoTipoService serviceTipo, VehiculoMarcaService vehiculoMarcaService) {
        this.vehiculoTipoView = vehiculoTipoView;
        this.serviceTipo = serviceTipo;
        this.vehiculoMarcaService = vehiculoMarcaService;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(tipo, marca, buttons);
        tipo.setRequired(true);
        marca.setRequired(true);
        marca.setItems(vehiculoMarcaService.listarMarca());
        marca.setItemLabelGenerator(VehiculoMarca::getMarca);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
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
        if(binder.isValid()) {
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
        serviceTipo.borrarTipo(tipo);
        this.vehiculoTipoView.updateList();
        setTipo(null);
    }
}
