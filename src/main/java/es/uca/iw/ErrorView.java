package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "AccesoDenegado", layout = MainView.class)
public class ErrorView extends VerticalLayout {
    public ErrorView() {
        Label content = new Label(
                "Pagina inaccesible");
        Button buttonInside = new Button("Cerrar");
        Notification notification = new Notification(content, buttonInside);
        notification.open();
        buttonInside.addClickListener(event -> {
            UI.getCurrent().navigate("");
            notification.close();
        });
        notification.setPosition(Notification.Position.MIDDLE);
    }
}