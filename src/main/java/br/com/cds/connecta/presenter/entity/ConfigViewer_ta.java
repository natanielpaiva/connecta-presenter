package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;


/**
 * The persistent class for the TA_CONFIG_VIEWER database table.
 * 
 */
@Entity
@Table(name="TA_CONFIG_VIEWER")
@NamedQuery(name="ConfigViewer_ta.findAll", query="SELECT t FROM ConfigViewer_ta t")
public class ConfigViewer_ta extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_CONFIG_VIEWER_PKTACONFIGVIEWER_GENERATOR", sequenceName="TA_CONFIG_VIEWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_CONFIG_VIEWER_PKTACONFIGVIEWER_GENERATOR")
	@Column(name="PK_TA_CONFIG_VIEWER")
	private Long id;

	@Column(name="TXT_VALUE")
	private String txtValue;

	//bi-directional many-to-one association to TbAnalysisViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS_VIEWER")
	private AnalysisViewer tbAnalysisViewer;

	//bi-directional many-to-one association to TbConfigViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_CONFIG_VIEWER")
	private ConfigViewer tbConfigViewer;

	public ConfigViewer_ta() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxtValue() {
		return this.txtValue;
	}

	public void setTxtValue(String txtValue) {
		this.txtValue = txtValue;
	}

	public AnalysisViewer getTbAnalysisViewer() {
		return this.tbAnalysisViewer;
	}

	public void setTbAnalysisViewer(AnalysisViewer tbAnalysisViewer) {
		this.tbAnalysisViewer = tbAnalysisViewer;
	}

	public ConfigViewer getTbConfigViewer() {
		return this.tbConfigViewer;
	}

	public void setTbConfigViewer(ConfigViewer tbConfigViewer) {
		this.tbConfigViewer = tbConfigViewer;
	}

}