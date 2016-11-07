package br.com.cds.connecta.presenter.components.viewers.chartjs;

import java.io.Serializable;

public class ChartJsElements implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Line line;

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
}