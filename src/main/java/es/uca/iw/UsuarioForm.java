package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.validator.EmailValidator;


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
    private Button save = new Button("Continuar");

    public UsuarioForm(UsuarioView usuarioView, UsuarioService service) {
        if(UI.getCurrent().getSession().getAttribute(Usuario.class) != null) {
            Usuario usuario = UI.getCurrent().getSession().getAttribute(Usuario.class);
            usuario.setPassword("");
            binder.setBean(usuario);
        }
        this.usuarioView = usuarioView;
        this.service = service;

        nombre.setRequired(true);
        apellido1.setRequired(true);
        apellido2.setRequired(true);
        dni.setRequired(true);
        email.setRequiredIndicatorVisible(true);
        password.setRequired(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(username, nombre, apellido1, apellido2, dni, telefono, email, password, save);

        binder.bindInstanceFields(this);

        Dialog dialog = new Dialog();

        Button confirmButton = new Button("Confirmar", event -> {
            save();
            dialog.close();
        });
        confirmButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        Button cancelButton = new Button("Cancelar", event -> {
            dialog.close();
        });
        dialog.add(confirmButton, cancelButton);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addClickListener(event -> dialog.open());
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
        binder.forField(email)
                // Explicit validator instance
                .withValidator(new EmailValidator(
                        "No es un formato válido. Ejemplo de formato válido: usuario@gmail.com"))
                .bind(Usuario::getEmail, Usuario::setEmail);
        if(binder.validate().isOk()) {
            if(UI.getCurrent().getSession().getAttribute(Usuario.class) != null) {
                Long id = UI.getCurrent().getSession().getAttribute(Usuario.class).getId();
                service.delete(UI.getCurrent().getSession().getAttribute(Usuario.class));
                service.create(usuario);
                service.updateId(id, UI.getCurrent().getSession().getAttribute(Usuario.class).getUsername());
                UI.getCurrent().navigate("");
            } else {
                service.create(usuario);
                UI.getCurrent().navigate("Login");
            }
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }


}
