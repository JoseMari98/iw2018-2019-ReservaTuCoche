package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("LogoutView")
public class LogoutView extends VerticalLayout {
    public LogoutView() {
        Label content = new Label(
                "Sesión finalizada\n");
        Button buttonInside = new Button("Regresar a inicio");
        Notification notification = new Notification(content, buttonInside);
        notification.open();
        buttonInside.addClickListener(event -> {
            UI.getCurrent().navigate("");
            notification.close();
        });
        notification.setPosition(Notification.Position.MIDDLE);
    }

}
