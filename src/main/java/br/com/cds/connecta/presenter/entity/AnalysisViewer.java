package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;

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
 * The persistent class for the TB_ANALYSIS_VIEWER database table.
 * 
 */
@Entity
@Table(name="TB_ANALYSIS_VIEWER")
@NamedQuery(name="AnalysisViewer.findAll", query="SELECT t FROM AnalysisViewer t")
public class AnalysisViewer extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ANALYSIS_VIEWER_PKANALYSISVIEWER_GENERATOR", sequenceName="TB_ANALYSIS_VIEWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ANALYSIS_VIEWER_PKANALYSISVIEWER_GENERATOR")
	@Column(name="PK_ANALYSIS_VIEWER")
	private Long id;

	@Column(name="NM_LABEL")
	private String nmLabel;

	@Column(name="NU_INTRVL_ATLZCO")
	private BigDecimal nuIntrvlAtlzco;

	@Column(name="NU_MAX_LINHAS")
	private BigDecimal nuMaxLinhas;

	//bi-directional many-to-one association to TbViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_VIEWER")
	private Viewer tbViewer;

	public AnalysisViewer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmLabel() {
		return this.nmLabel;
	}

	public void setNmLabel(String nmLabel) {
		this.nmLabel = nmLabel;
	}

	public BigDecimal getNuIntrvlAtlzco() {
		return this.nuIntrvlAtlzco;
	}

	public void setNuIntrvlAtlzco(BigDecimal nuIntrvlAtlzco) {
		this.nuIntrvlAtlzco = nuIntrvlAtlzco;
	}

	public BigDecimal getNuMaxLinhas() {
		return this.nuMaxLinhas;
	}

	public void setNuMaxLinhas(BigDecimal nuMaxLinhas) {
		this.nuMaxLinhas = nuMaxLinhas;
	}

	public Viewer getTbViewer() {
		return this.tbViewer;
	}

	public void setTbViewer(Viewer tbViewer) {
		this.tbViewer = tbViewer;
	}

}