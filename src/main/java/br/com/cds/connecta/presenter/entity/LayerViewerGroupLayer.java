package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;


/**
 * The persistent class for the TA_LAYER_VIEWER_GROUP_LAYER database table.
 * 
 */
@Entity
@Table(name="TA_LAYER_VIEWER_GROUP_LAYER")
@NamedQuery(name="LayerViewerGroupLayer.findAll", query="SELECT t FROM LayerViewerGroupLayer t")
public class LayerViewerGroupLayer extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_LAYER_VIEWER_GROUP_LAYER_IDRELATION_GENERATOR", sequenceName="TA_LAYER_VIEWER_GROUP_LAYER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_LAYER_VIEWER_GROUP_LAYER_IDRELATION_GENERATOR")
	@Column(name="ID_RELATION")
	private Long id;

	@Column(name="ID_VIEWER")
	private BigDecimal idViewer;

	@Column(name="ID_VIEWER_GROUP")
	private BigDecimal idViewerGroup;

	public LayerViewerGroupLayer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getIdViewer() {
		return this.idViewer;
	}

	public void setIdViewer(BigDecimal idViewer) {
		this.idViewer = idViewer;
	}

	public BigDecimal getIdViewerGroup() {
		return this.idViewerGroup;
	}

	public void setIdViewerGroup(BigDecimal idViewerGroup) {
		this.idViewerGroup = idViewerGroup;
	}

}