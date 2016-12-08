package br.com.cds.connecta.presenter.components.viewers.chartjs;

import java.io.Serializable;

public class ChartJsTooltips implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean enabled;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}