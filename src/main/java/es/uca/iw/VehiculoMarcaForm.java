package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class VehiculoMarcaForm extends FormLayout {
    private TextField marca = new TextField("Marca");
    private Button save = new Button("Añadir");
    private Button delete = new Button("Borrar");
    private VehiculoMarcaGestionView vehiculoMarcaView;
    private Binder<VehiculoMarca> binder = new Binder<>(VehiculoMarca.class);
    private VehiculoMarcaService serviceMarca;
    private VehiculoTipoService serviceTipo;

    public VehiculoMarcaForm(VehiculoMarcaGestionView vehiculoMarcaView, VehiculoMarcaService serviceMarca, VehiculoTipoService serviceTipo) {
        this.vehiculoMarcaView = vehiculoMarcaView;
        this.serviceMarca = serviceMarca;
        this.serviceTipo = serviceTipo;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(marca, buttons);
        marca.setRequired(true);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setMarca(VehiculoMarca marca) {
        binder.setBean(marca);

        if(marca == null) {
            setVisible(false);
        }
        else {
            setVisible(true);
            this.marca.focus();
        }
    }

    public void save() {
        VehiculoMarca marca = binder.getBean();
        if(binder.isValid()) {
            serviceMarca.guardarMarca(marca);
            this.vehiculoMarcaView.updateList();
            setMarca(null);
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        VehiculoMarca marca = binder.getBean();
        for(VehiculoTipo tipo : serviceTipo.listarPorMarca(marca))
            serviceTipo.borrarTipo(tipo);
        serviceMarca.borrarMarca(marca);
        this.vehiculoMarcaView.updateList();
        setMarca(null);
    }
}
