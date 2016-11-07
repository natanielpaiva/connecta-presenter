package br.com.cds.connecta.presenter.components.viewers.chartjs;

import java.io.Serializable;

public class ChartJsOptions implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ChartJsElements elements;

	public ChartJsElements getElements() {
		return elements;
	}

	public void setElements(ChartJsElements elements) {
		this.elements = elements;
	}
	
}