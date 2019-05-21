package es.uca.iw;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import java.nio.file.AccessDeniedException;

public class AbstractView extends VerticalLayout implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        final boolean accessGranted = SecurityUtils.isAccessGranted(event.getNavigationTarget());

        if(!accessGranted) {
            if(SecurityUtils.isUserLoggedIn()) {
                event.rerouteToError(AccessDeniedException.class);
            }
            else {
                event.rerouteTo(Login.class);
            }
        }
    }
}
