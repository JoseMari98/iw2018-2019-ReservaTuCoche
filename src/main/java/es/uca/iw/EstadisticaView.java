package es.uca.iw;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import com.vaadin.flow.component.notification.Notification;


@Route(value = "Charts", layout = MainView.class)
@Secured("Gerente")
public class EstadisticaView extends AbstractView {
    private VehiculoService serviceVehiculo;
    private VehiculoMarcaService serviceMarca;
    private VehiculoModeloService serviceModelo;
    private ReservaService serviceReserva;

    @Autowired
    public EstadisticaView(VehiculoService serviceVehiculo, VehiculoMarcaService serviceMarca, VehiculoModeloService serviceModelo, ReservaService serviceReserva) {
        this.serviceVehiculo = serviceVehiculo;
        this.serviceMarca = serviceMarca;
        this.serviceModelo = serviceModelo;
        this.serviceReserva = serviceReserva;
        if(serviceReserva.count() != 0) {
            Chart chart = new Chart();

            Configuration configuration = chart.getConfiguration();
            configuration.setTitle("Nº de reservas realizadas por marca");
            chart.getConfiguration().getChart().setType(ChartType.BAR);


            for (VehiculoMarca marca : serviceMarca.listarMarca()) {
                int iDato = 0;
                for (Reserva reserva : serviceReserva.findAll()) {
                    if (reserva.getVehiculo().getMarca().getMarca().equals(marca.getMarca())) {
                        ++iDato;
                    }
                }
                configuration.addSeries(new ListSeries(marca.getMarca(), iDato));
            }


            XAxis x = new XAxis();
            x.setCategories("Marcas");
            configuration.addxAxis(x);

            YAxis y = new YAxis();
            y.setMin(0);
            AxisTitle yTitle = new AxisTitle();
            yTitle.setText("Reservas realizadas");
            yTitle.setAlign(VerticalAlign.HIGH);
            y.setTitle(yTitle);
            configuration.addyAxis(y);

            Tooltip tooltip = new Tooltip();
            tooltip.setValueSuffix(" Reservas realizadas");
            configuration.setTooltip(tooltip);

            PlotOptionsBar plotOptions = new PlotOptionsBar();
            DataLabels dataLabels = new DataLabels();
            dataLabels.setEnabled(true);
            plotOptions.setDataLabels(dataLabels);
            configuration.setPlotOptions(plotOptions);

            Chart chartModelo = new Chart();

            Configuration configurationModelo = chartModelo.getConfiguration();
            configurationModelo.setTitle("Nº de reservas realizadas por modelo");
            chartModelo.getConfiguration().getChart().setType(ChartType.BAR);


            for (VehiculoModelo modelo : serviceModelo.listarModelo()) {
                int iDato = 0;
                for (Reserva reserva : serviceReserva.findAll()) {
                    if (reserva.getVehiculo().getModelo().getModelo().equals(modelo.getModelo())) {
                        ++iDato;
                    }
                }
                configurationModelo.addSeries(new ListSeries(modelo.getModelo(), iDato));
            }


            XAxis xModelo = new XAxis();
            xModelo.setCategories("Modelos");
            configurationModelo.addxAxis(xModelo);

            YAxis yModelo = new YAxis();
            yModelo.setMin(0);
            AxisTitle yTitleModelo = new AxisTitle();
            yTitleModelo.setText("Reservas realizadas");
            yTitleModelo.setAlign(VerticalAlign.HIGH);
            yModelo.setTitle(yTitleModelo);
            configurationModelo.addyAxis(yModelo);

            Tooltip tooltipModelo = new Tooltip();
            tooltipModelo.setValueSuffix(" Reservas realizadas");
            configurationModelo.setTooltip(tooltipModelo);

            PlotOptionsBar plotOptionsModelo = new PlotOptionsBar();
            DataLabels dataLabelsModelo = new DataLabels();
            dataLabelsModelo.setEnabled(true);
            plotOptionsModelo.setDataLabels(dataLabelsModelo);
            configurationModelo.setPlotOptions(plotOptionsModelo);

            add(chart, chartModelo);

        }
        Notification.show("No hay ninguna reserva", 3000, Notification.Position.MIDDLE);
        UI.getCurrent().navigate("");
    }
}
