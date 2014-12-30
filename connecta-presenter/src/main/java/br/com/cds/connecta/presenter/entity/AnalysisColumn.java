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
 * The persistent class for the TB_ANALYSIS_COLUMNS database table.
 * 
 */
@Entity
@Table(name="TB_ANALYSIS_COLUMNS")
@NamedQuery(name="AnalysisColumn.findAll", query="SELECT t FROM AnalysisColumn t")
public class AnalysisColumn extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ANALYSIS_COLUMNS_PKANALYSISCOLUMNS_GENERATOR", sequenceName="TB_ANALYSIS_COLUMNS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ANALYSIS_COLUMNS_PKANALYSISCOLUMNS_GENERATOR")
	@Column(name="PK_ANALYSIS_COLUMNS")
	private Long id;

	@Column(name="FML_COLUMN")
	private String fmlColumn;

	@Column(name="LB_COLUMN")
	private String lbColumn;

	@Column(name="NM_COLUMN")
	private String nmColumn;

	@Column(name="TP_COLUMN")
	private BigDecimal tpColumn;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS")
	private Analysi tbAnalysi;

	//bi-directional many-to-one association to TbCombinedAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_COMBINED_ANALYSIS")
	private CombinedAnalysi tbCombinedAnalysi;

	public AnalysisColumn() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFmlColumn() {
		return this.fmlColumn;
	}

	public void setFmlColumn(String fmlColumn) {
		this.fmlColumn = fmlColumn;
	}

	public String getLbColumn() {
		return this.lbColumn;
	}

	public void setLbColumn(String lbColumn) {
		this.lbColumn = lbColumn;
	}

	public String getNmColumn() {
		return this.nmColumn;
	}

	public void setNmColumn(String nmColumn) {
		this.nmColumn = nmColumn;
	}

	public BigDecimal getTpColumn() {
		return this.tpColumn;
	}

	public void setTpColumn(BigDecimal tpColumn) {
		this.tpColumn = tpColumn;
	}

	public Analysi getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysi tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

	public CombinedAnalysi getTbCombinedAnalysi() {
		return this.tbCombinedAnalysi;
	}

	public void setTbCombinedAnalysi(CombinedAnalysi tbCombinedAnalysi) {
		this.tbCombinedAnalysi = tbCombinedAnalysi;
	}

}