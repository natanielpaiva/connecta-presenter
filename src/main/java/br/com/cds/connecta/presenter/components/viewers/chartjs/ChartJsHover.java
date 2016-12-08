package br.com.cds.connecta.presenter.components.viewers.chartjs;

import java.io.Serializable;

public class ChartJsHover implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer animationDuration;

	public Integer getAnimationDuration() {
		return animationDuration;
	}

	public void setAnimationDuration(Integer animationDuration) {
		this.animationDuration = animationDuration;
	}

}