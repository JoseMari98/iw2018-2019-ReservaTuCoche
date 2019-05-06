package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("login")

@Theme(Material.class)

public class Loginusuario extends VerticalLayout {

    UsuarioService service;
    AuthenticationManager authenticationManager;
    LoginOverlay loginForm;

    @Autowired
    public Loginusuario(UsuarioService service, AuthenticationManager authenticationManager)
    {
        this.service = service;
        this.authenticationManager = authenticationManager;
        loginForm = new LoginOverlay();
        loginForm.setOpened(true);
        loginForm.setTitle("Reserva tu coche");
        loginForm.setDescription("Inicio sesión");
        loginForm.setForgotPasswordButtonVisible(false);
        loginForm.addLoginListener( e -> signIn(e));
    }

    private void signIn(AbstractLogin.LoginEvent e)
    {
        if (authenticate(e.getUsername(), e.getPassword()))
        {
            loginForm.close();
            UI.getCurrent().navigate(MainView.class);
        } else {
            loginForm.setError(true);
        }
    }

    public boolean authenticate(String username, String password)
    {
        try{
            Authentication token = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(username,password));
            SecurityContextHolder.getContext().setAuthentication(token);
            return true;
        } catch (AuthenticationException ex) {
            return false;
        }
    }
}
