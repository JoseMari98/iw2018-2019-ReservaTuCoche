package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class VehiculoModeloForm extends FormLayout {
    private TextField modelo = new TextField("Modelo");
    private Button save = new Button("Añadir");
    private Button delete = new Button("Borrar");
    private VehiculoModeloGestionView vehiculoModeloView;
    private Binder<VehiculoModelo> binder = new Binder<>(VehiculoModelo.class);
    private VehiculoModeloService serviceModelo;

    public VehiculoModeloForm(VehiculoModeloGestionView vehiculoModeloView, VehiculoModeloService serviceModelo) {
        this.vehiculoModeloView = vehiculoModeloView;
        this.serviceModelo = serviceModelo;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(modelo, buttons);
        modelo.setRequired(true);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setModelo(VehiculoModelo modelo) {
        binder.setBean(modelo);

        if(modelo == null) {
            setVisible(false);
        }
        else {
            setVisible(true);
            this.modelo.focus();
        }
    }

    public void save() {
        VehiculoModelo modelo = binder.getBean();
        if(binder.isValid()) {
            serviceModelo.guardarModelo(modelo);
            this.vehiculoModeloView.updateList();
            setModelo(null);
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        VehiculoModelo modelo = binder.getBean();
        serviceModelo.borrarModelo(modelo);
        this.vehiculoModeloView.updateList();
        setModelo(null);
    }
}
