package br.com.cds.connecta.presenter.components.viewers;

import br.com.cds.connecta.framework.amcharts.model.ViewerConfiguration;
import br.com.cds.connecta.presenter.components.viewers.amcharts.AmAngularGauge;
import br.com.cds.connecta.presenter.components.viewers.amcharts.AmFunnelChart;
import br.com.cds.connecta.presenter.components.viewers.amcharts.AmPieChart;
import br.com.cds.connecta.presenter.components.viewers.amcharts.AmRadarChart;
import br.com.cds.connecta.presenter.components.viewers.amcharts.AmSerialChart;
import br.com.cds.connecta.presenter.components.viewers.amcharts.AmXYChart;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(name = "serial", value = AmSerialChart.class),
    @JsonSubTypes.Type(name = "pie", value = AmPieChart.class),
    @JsonSubTypes.Type(name = "xy", value = AmXYChart.class),
    @JsonSubTypes.Type(name = "radar", value = AmRadarChart.class),
    @JsonSubTypes.Type(name = "funnel", value = AmFunnelChart.class),
    @JsonSubTypes.Type(name = "gauge", value = AmAngularGauge.class),
    @JsonSubTypes.Type(name = "singlesource", value = SingleSourceViewerConfiguration.class),
    @JsonSubTypes.Type(name = "singlesourcegroup", value = SingleSourceGroupViewerConfiguration.class),
    @JsonSubTypes.Type(name = "table", value = TableViewerConfiguration.class),
    @JsonSubTypes.Type(name = "chartjs", value = ChartJsViewerConfiguration.class)
})
public interface PresenterViewerConfiguration extends ViewerConfiguration {
    
}
