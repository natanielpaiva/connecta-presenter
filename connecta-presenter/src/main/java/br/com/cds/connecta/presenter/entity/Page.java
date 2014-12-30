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
 * The persistent class for the TB_PAGE database table.
 * 
 */
@Entity
@Table(name="TB_PAGE")
@NamedQuery(name="Page.findAll", query="SELECT t FROM Page t")
public class Page extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PAGE_PKPAGE_GENERATOR", sequenceName="TB_PAGE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PAGE_PKPAGE_GENERATOR")
	@Column(name="PK_PAGE")
	private Long id;

	@Column(name="DS_PAGE")
	private String dsPage;

	@Column(name="FLG_INFINITY_SCROLL")
	private String flgInfinityScroll;

	@Column(name="NM_PAGE")
	private String nmPage;

	@Column(name="NU_COLUMNS")
	private BigDecimal nuColumns;

	@Column(name="NU_GITTER")
	private BigDecimal nuGitter;

	@Column(name="NU_LINES")
	private BigDecimal nuLines;

	@Column(name="NU_ORDER")
	private BigDecimal nuOrder;

	@Column(name="NU_TRANSITION_TIME")
	private BigDecimal nuTransitionTime;

	@Column(name="TP_EFFECT")
	private String tpEffect;

	@Column(name="TP_PAGE")
	private String tpPage;
	
	//bi-directional many-to-one association to TbDashboard
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_DASHBOARD")
	private Dashboard tbDashboard;

	public Page() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsPage() {
		return this.dsPage;
	}

	public void setDsPage(String dsPage) {
		this.dsPage = dsPage;
	}

	public String getFlgInfinityScroll() {
		return this.flgInfinityScroll;
	}

	public void setFlgInfinityScroll(String flgInfinityScroll) {
		this.flgInfinityScroll = flgInfinityScroll;
	}

	public String getNmPage() {
		return this.nmPage;
	}

	public void setNmPage(String nmPage) {
		this.nmPage = nmPage;
	}

	public BigDecimal getNuColumns() {
		return this.nuColumns;
	}

	public void setNuColumns(BigDecimal nuColumns) {
		this.nuColumns = nuColumns;
	}

	public BigDecimal getNuGitter() {
		return this.nuGitter;
	}

	public void setNuGitter(BigDecimal nuGitter) {
		this.nuGitter = nuGitter;
	}

	public BigDecimal getNuLines() {
		return this.nuLines;
	}

	public void setNuLines(BigDecimal nuLines) {
		this.nuLines = nuLines;
	}

	public BigDecimal getNuOrder() {
		return this.nuOrder;
	}

	public void setNuOrder(BigDecimal nuOrder) {
		this.nuOrder = nuOrder;
	}

	public BigDecimal getNuTransitionTime() {
		return this.nuTransitionTime;
	}

	public void setNuTransitionTime(BigDecimal nuTransitionTime) {
		this.nuTransitionTime = nuTransitionTime;
	}

	public String getTpEffect() {
		return this.tpEffect;
	}

	public void setTpEffect(String tpEffect) {
		this.tpEffect = tpEffect;
	}

	public String getTpPage() {
		return this.tpPage;
	}

	public void setTpPage(String tpPage) {
		this.tpPage = tpPage;
	}

	public Dashboard getTbDashboard() {
		return this.tbDashboard;
	}

	public void setTbDashboard(Dashboard tbDashboard) {
		this.tbDashboard = tbDashboard;
	}
}