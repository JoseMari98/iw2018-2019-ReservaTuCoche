package es.uca.iw;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;


public class UsuarioForm extends FormLayout {

    private TextField nombre = new TextField("Nombre");
    private TextField apellido1 = new TextField("Primer Apellido");
    private TextField apellido2 = new TextField("Segundo Apellido");
    private TextField dni = new TextField("Dni");
    private TextField telefono = new TextField("Teléfono");
    private EmailField email = new EmailField("Email");
    private PasswordField contrasena = new PasswordField("Contraseña");

    private BeanValidationBinder<Usuario> binder = new BeanValidationBinder<>(Usuario.class);
    private UsuarioView usuarioView;
    private UsuarioService service;



    private Button save = new Button("Registrarse");

    public UsuarioForm(UsuarioView usuarioView, UsuarioService service) {

        this.usuarioView = usuarioView;
        this.service = service;

        nombre.setRequired(true);
        apellido1.setRequired(true);
        apellido2.setRequired(true);
        dni.setRequired(true);
        email.setRequiredIndicatorVisible(true);
        contrasena.setRequired(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nombre, apellido1,apellido2, dni, telefono, email, contrasena, save);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());

        setSizeFull();

    }

    public void save() {
        Usuario usuario = binder.getBean();
        if(binder.validate().isOk()) {
            service.create(usuario);
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }


}
