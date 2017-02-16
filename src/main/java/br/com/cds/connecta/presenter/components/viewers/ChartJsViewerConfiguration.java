package br.com.cds.connecta.presenter.components.viewers;

import java.util.List;

import br.com.cds.connecta.presenter.components.viewers.chartjs.ChartJsOptions;

public class ChartJsViewerConfiguration implements PresenterViewerConfiguration {
	
	private static final long serialVersionUID = 1L;
	
	private String subtype;
	
	private ChartJsOptions options;
	
	private List<String> colors;

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public ChartJsOptions getOptions() {
		return options;
	}

	public void setOptions(ChartJsOptions options) {
		this.options = options;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

}
