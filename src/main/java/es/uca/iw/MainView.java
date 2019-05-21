package es.uca.iw;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

/**
 * The main view contains a button and a click listener.
 */
@Theme(Material.class)
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends AbstractAppRouterLayout {

    public MainView(){}
    @Override
    protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
        appLayoutMenu = appLayout.createMenu();
        Icon user = new Icon(VaadinIcon.USER);
        //appLayoutMenu.addMenuItems(new AppLayoutMenuItem("Iniciar sesión", "Login"), new AppLayoutMenuItem("Registrarse","RegistroUsuario"));
        appLayoutMenu.addMenuItem(user, "Inicar sesión", "Login");
    }

    private void setMenuItem(AppLayoutMenu appLayoutMenu) {
    }
}
