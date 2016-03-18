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
 * The persistent class for the TB_RELATION_CMB_ANALYSIS database table.
 * 
 */
@Entity
@Table(name="TB_RELATION_CMB_ANALYSIS")
@NamedQuery(name="RelationCmbAnalysi.findAll", query="SELECT t FROM RelationCmbAnalysi t")
public class RelationCmbAnalysi extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
        @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PK_RELATION_CMB_ANALYSIS")
	private Long id;

	@Column(name="ID_SOURCE1")
	private BigDecimal idSource1;

	@Column(name="ID_SOURCE2")
	private BigDecimal idSource2;

	@Column(name="NU_ORDER")
	private BigDecimal nuOrder;

	@Column(name="TP_SOURCE1")
	private String tpSource1;

	@Column(name="TP_SOURCE2")
	private String tpSource2;

	//bi-directional many-to-one association to TbCombinedAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_COMBINED_ANALYSIS")
	private CombinedAnalysis tbCombinedAnalysi;

	public RelationCmbAnalysi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getIdSource1() {
		return this.idSource1;
	}

	public void setIdSource1(BigDecimal idSource1) {
		this.idSource1 = idSource1;
	}

	public BigDecimal getIdSource2() {
		return this.idSource2;
	}

	public void setIdSource2(BigDecimal idSource2) {
		this.idSource2 = idSource2;
	}

	public BigDecimal getNuOrder() {
		return this.nuOrder;
	}

	public void setNuOrder(BigDecimal nuOrder) {
		this.nuOrder = nuOrder;
	}

	public String getTpSource1() {
		return this.tpSource1;
	}

	public void setTpSource1(String tpSource1) {
		this.tpSource1 = tpSource1;
	}

	public String getTpSource2() {
		return this.tpSource2;
	}

	public void setTpSource2(String tpSource2) {
		this.tpSource2 = tpSource2;
	}

	public CombinedAnalysis getTbCombinedAnalysi() {
		return this.tbCombinedAnalysi;
	}

	public void setTbCombinedAnalysi(CombinedAnalysis tbCombinedAnalysi) {
		this.tbCombinedAnalysi = tbCombinedAnalysi;
	}
}