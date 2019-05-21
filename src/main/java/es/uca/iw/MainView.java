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
        Icon user = new Icon(VaadinIcon.SIGN_IN);
        Icon home = new Icon(VaadinIcon.HOME);
        Icon registro = new Icon(VaadinIcon.USER_CARD);
        Icon reserva = new Icon(VaadinIcon.CAR);
        Icon cerrar = new Icon(VaadinIcon.SIGN_OUT);
        Icon gestion = new Icon(VaadinIcon.RECORDS);
        Icon estadistica = new Icon(VaadinIcon.SPLINE_AREA_CHART);
        Icon postpago = new Icon(VaadinIcon.MONEY);

        appLayoutMenu.addMenuItem(home, "Home", "");

        if(!SecurityUtils.isUserLoggedIn())
        {
            appLayoutMenu.addMenuItem(user, "Inicar Sesión", "Login");
            appLayoutMenu.addMenuItem(registro, "Registrarse", "RegistroUsuario");
        }
        else {
            if(SecurityUtils.hasRole("User")){

                appLayoutMenu.addMenuItem(reserva, "Mis Reservas", "MisReservas");
            }

            if(SecurityUtils.hasRole("Admin"))
            {
                appLayoutMenu.addMenuItem(gestion, "Gestión Vehículo", "GestionVehiculo");
                appLayoutMenu.addMenuItem(gestion, "Gestión Reservas", "GestionReservas");
                appLayoutMenu.addMenuItem(postpago, "Gestión Postpago", "GestionPostpago");
                appLayoutMenu.addMenuItem(gestion, "Gestión Modelo", "GestionModelo");
                appLayoutMenu.addMenuItem(gestion, "Gestión Marca", "GestionMarca");
                appLayoutMenu.addMenuItem(estadistica, "Estadísticas", "Estadísticas");
            }

            appLayoutMenu.addMenuItem(cerrar, "Cerrar Sesión", "CerrarSesion");
        }


    }
}
