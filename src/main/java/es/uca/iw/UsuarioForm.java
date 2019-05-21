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

    private TextField username = new TextField("Nombre usuario");
    private TextField nombre = new TextField("Nombre");
    private TextField apellido1 = new TextField("Primer Apellido");
    private TextField apellido2 = new TextField("Segundo Apellido");
    private TextField dni = new TextField("Dni");
    private TextField telefono = new TextField("Teléfono");
    private EmailField email = new EmailField("Email");
    private PasswordField password = new PasswordField("Contraseña");

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
        password.setRequired(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(username, nombre, apellido1,apellido2, dni, telefono, email, password, save);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
    }

    public void save() {
        Usuario usuario = new Usuario();
        usuario.setRole("User");
        usuario.setUsername(username.getValue());
        usuario.setNombre(nombre.getValue());
        usuario.setApellido1(apellido1.getValue());
        usuario.setApellido2(apellido2.getValue());
        usuario.setDni(dni.getValue());
        usuario.setTelefono(telefono.getValue());
        usuario.setEmail(email.getValue());
        usuario.setPassword(password.getValue());
        if(binder.validate().isOk()) {
            service.create(usuario);
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }


}
