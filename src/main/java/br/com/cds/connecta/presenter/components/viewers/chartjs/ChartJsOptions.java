package br.com.cds.connecta.presenter.components.viewers.chartjs;

import java.io.Serializable;

public class ChartJsOptions implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean events;
	
	private ChartJsTooltips tooltips;
	
	private ChartJsElements elements;
	
	private ChartJsHover hover;
	
	private ChartJsAnimation animation;

	public ChartJsAnimation getAnimation() {
		return animation;
	}

	public void setAnimation(ChartJsAnimation animation) {
		this.animation = animation;
	}

	public Boolean getEvents() {
		return events;
	}

	public void setEvents(Boolean events) {
		this.events = events;
	}

	public ChartJsHover getHover() {
		return hover;
	}

	public void setHover(ChartJsHover hover) {
		this.hover = hover;
	}

	public ChartJsTooltips getTooltips() {
		return tooltips;
	}

	public void setTooltips(ChartJsTooltips tooltips) {
		this.tooltips = tooltips;
	}

	public ChartJsElements getElements() {
		return elements;
	}

	public void setElements(ChartJsElements elements) {
		this.elements = elements;
	}
	
}