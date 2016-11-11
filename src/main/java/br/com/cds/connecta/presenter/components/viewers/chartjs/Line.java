package br.com.cds.connecta.presenter.components.viewers.chartjs;

import java.io.Serializable;

public class Line implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean fill;

	public Boolean getFill() {
		return fill;
	}

	public void setFill(Boolean fill) {
		this.fill = fill;
	}
	
}