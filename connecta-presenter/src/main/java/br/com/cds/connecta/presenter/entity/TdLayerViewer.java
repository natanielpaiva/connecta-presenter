package br.com.cds.connecta.presenter.entity;

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
 * The persistent class for the TD_LAYER_VIEWER database table.
 * 
 */
@Entity
@Table(name="TD_LAYER_VIEWER")
@NamedQuery(name="TdLayerViewer.findAll", query="SELECT t FROM TdLayerViewer t")
public class TdLayerViewer extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TD_LAYER_VIEWER_IDTPVIEWER_GENERATOR", sequenceName="TD_LAYER_VIEWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TD_LAYER_VIEWER_IDTPVIEWER_GENERATOR")
	@Column(name="ID_TP_VIEWER")
	private Long id;

	@Column(name="DS_TP_VIEWER")
	private String dsTpViewer;

	public TdLayerViewer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsTpViewer() {
		return this.dsTpViewer;
	}

	public void setDsTpViewer(String dsTpViewer) {
		this.dsTpViewer = dsTpViewer;
	}

}