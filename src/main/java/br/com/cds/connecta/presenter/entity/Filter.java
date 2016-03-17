package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
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

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.Table;


/**
 * The persistent class for the FILTER database table.
 * 
 */
@Entity
//@Table(name = "TB_FILTER")
@NamedQuery(name="Filter.findAll", query="SELECT f FROM Filter f")
public class Filter extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PK_FILTER")
	private Long id;

	@Column(name="NM_FILTER")
	private String nmFilter;

	@Column(name="TP_FILTER")
	private String tpFilter;

	@Column(name="TXT_COLUMN_LABEL")
	private String txtColumnLabel;

	@Column(name="TXT_COLUMN_VALUE")
	private String txtColumnValue;

	@Column(name="TXT_SELECTED_VALUE")
	private String txtSelectedValue;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS")
	private Analysis tbAnalysi;

	//bi-directional many-to-one association to TbLayer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_LAYER")
	private Layer tbLayer;

	public Filter() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmFilter() {
		return this.nmFilter;
	}

	public void setNmFilter(String nmFilter) {
		this.nmFilter = nmFilter;
	}

	public String getTpFilter() {
		return this.tpFilter;
	}

	public void setTpFilter(String tpFilter) {
		this.tpFilter = tpFilter;
	}

	public String getTxtColumnLabel() {
		return this.txtColumnLabel;
	}

	public void setTxtColumnLabel(String txtColumnLabel) {
		this.txtColumnLabel = txtColumnLabel;
	}

	public String getTxtColumnValue() {
		return this.txtColumnValue;
	}

	public void setTxtColumnValue(String txtColumnValue) {
		this.txtColumnValue = txtColumnValue;
	}

	public String getTxtSelectedValue() {
		return this.txtSelectedValue;
	}

	public void setTxtSelectedValue(String txtSelectedValue) {
		this.txtSelectedValue = txtSelectedValue;
	}

	public Analysis getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysis tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

	public Layer getTbLayer() {
		return this.tbLayer;
	}

	public void setTbLayer(Layer tbLayer) {
		this.tbLayer = tbLayer;
	}

}