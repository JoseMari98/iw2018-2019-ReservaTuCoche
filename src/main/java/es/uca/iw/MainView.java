package es.uca.iw;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * The main view contains a button and a click listener.
 */
@Theme(Material.class)
@PWA(name = "Reserva Tu Coche", shortName = "RTC")
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
        Icon gestion1 = new Icon(VaadinIcon.RECORDS);
        Icon gestion2 = new Icon(VaadinIcon.RECORDS);
        Icon gestion3 = new Icon(VaadinIcon.RECORDS);
        Icon gestion4 = new Icon(VaadinIcon.RECORDS);
        Icon estadistica = new Icon(VaadinIcon.SPLINE_AREA_CHART);
        Icon postpago = new Icon(VaadinIcon.EURO);
        Icon tarjetas = new Icon(VaadinIcon.USER_CARD);
        Icon modificarusuario = new Icon(VaadinIcon.COGS);


        appLayoutMenu.addMenuItem(home, "Home", "");

        if(!SecurityUtils.isUserLoggedIn())
        {
            appLayoutMenu.addMenuItem(user, "Inicar Sesión", "Login");
            appLayoutMenu.addMenuItem(registro, "Registrarse", "UsuarioView");
        }
        else {
            if(SecurityUtils.hasRole("User")){

                appLayoutMenu.addMenuItem(reserva, "Mis Reservas", "MisReservas");
                appLayoutMenu.addMenuItem(modificarusuario, "Modificar Usuario", "UsuarioView");
                appLayoutMenu.addMenuItem(tarjetas, "Mis Tarjetas", "MisTarjetas");
            }

            if(SecurityUtils.hasRole("Admin"))
            {
                appLayoutMenu.addMenuItem(gestion3, "Gestión Vehículo", "GestionVehiculo");
                appLayoutMenu.addMenuItem(gestion2, "Gestión Reservas", "GestionReservas");
                appLayoutMenu.addMenuItem(gestion1, "Gestión Modelo", "GestionModelo");
                appLayoutMenu.addMenuItem(gestion, "Gestión Marca", "GestionMarca");
                appLayoutMenu.addMenuItem(gestion4, "Gestión Tipo", "GestionTipo");
                appLayoutMenu.addMenuItem(postpago, "Gestión Postpago", "GestionPostpago");
                appLayoutMenu.addMenuItem(estadistica, "Estadísticas", "Estadísticas");
            }
            appLayoutMenu.addMenuItem(cerrar, "Cerrar Sesión", e -> signOut());;
        }
    }
    private void signOut() {
        UI.getCurrent().getPage().executeJavaScript("location.assign('logout')");
        UI.getCurrent().getSession().close();
        UI.getCurrent().getSession().setAttribute(Usuario.class, null);
        UI.getCurrent().setPollInterval(3000);
    }

   /* @Override
    public void showRouterLayoutContent(HasElement content) {
        if (content != null) {
            this.getElement().appendChild(Objects.requireNonNull(content.getElement()));
        }
    }*/
}
