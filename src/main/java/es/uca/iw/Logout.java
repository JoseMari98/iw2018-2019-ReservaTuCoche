package es.uca.iw;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "CerrarSesion", layout = MainView.class)
public class Logout extends Div {

    protected void onAttach(AttachEvent attachEvent) {
        UI ui = getUI().get();
        Button button = new Button("Logout", event -> {
            // Redirect this page immediately
            UI.getCurrent().navigate("LogoutView");
            // Close the session
            ui.getSession().close();
        });
        add(button);
        // Notice quickly if other UIs are closed
        ui.setPollInterval(3000);
    }
}