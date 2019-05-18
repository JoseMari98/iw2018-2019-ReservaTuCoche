package es.uca.iw;

import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@Route("Info")
public class VehiculoInfoView extends HttpServlet implements HasUrlParameter<Long> {

    private Long id;

    @Autowired
    public VehiculoInfoView(VehiculoService service, VehiculoModeloService modeloService, VehiculoMarcaService marcaService, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>Informacion</title></head>");
        out.println("<body> <h1>Informacion del vehiculo</h1></body></html>");
        out.close();
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        this.id = id;
    }
}
