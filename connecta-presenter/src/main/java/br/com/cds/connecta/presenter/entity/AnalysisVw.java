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
 * The persistent class for the TA_ANALYSIS_VW_COLUMNS database table.
 * 
 */
@Entity
@Table(name="TA_ANALYSIS_VW_COLUMNS")
@NamedQuery(name="AnalysisVw.findAll", query="SELECT t FROM AnalysisVw t")
public class AnalysisVw extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_ANALYSIS_VW_COLUMNS_PKANALYSISVWCOLUMNS_GENERATOR", sequenceName="TA_ANALYSIS_VW_COLUMNS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_ANALYSIS_VW_COLUMNS_PKANALYSISVWCOLUMNS_GENERATOR")
	@Column(name="PK_ANALYSIS_VW_COLUMNS")
	private Long id;

	@Column(name="TP_COLUMN")
	private String tpColumn;

	@Column(name="TP_ORDER")
	private String tpOrder;

	@Column(name="TP_TYPE")
	private String tpType;

	@Column(name="TXT_MASK_FORMAT")
	private String txtMaskFormat;

	//bi-directional many-to-one association to TbAnalysisViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS_VIEWER")
	private AnalysisViewer tbAnalysisViewer;

	//bi-directional many-to-one association to TbAnalysisColumn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS_COLUMNS")
	private AnalysisColumn tbAnalysisColumn;
	

	public AnalysisVw() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTpColumn() {
		return this.tpColumn;
	}

	public void setTpColumn(String tpColumn) {
		this.tpColumn = tpColumn;
	}

	public String getTpOrder() {
		return this.tpOrder;
	}

	public void setTpOrder(String tpOrder) {
		this.tpOrder = tpOrder;
	}

	public String getTpType() {
		return this.tpType;
	}

	public void setTpType(String tpType) {
		this.tpType = tpType;
	}

	public String getTxtMaskFormat() {
		return this.txtMaskFormat;
	}

	public void setTxtMaskFormat(String txtMaskFormat) {
		this.txtMaskFormat = txtMaskFormat;
	}

	public AnalysisViewer getTbAnalysisViewer() {
		return this.tbAnalysisViewer;
	}

	public void setTbAnalysisViewer(AnalysisViewer tbAnalysisViewer) {
		this.tbAnalysisViewer = tbAnalysisViewer;
	}

	public AnalysisColumn getTbAnalysisColumn() {
		return this.tbAnalysisColumn;
	}

	public void setTbAnalysisColumn(AnalysisColumn tbAnalysisColumn) {
		this.tbAnalysisColumn = tbAnalysisColumn;
	}

}