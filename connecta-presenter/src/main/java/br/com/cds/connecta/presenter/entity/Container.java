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
 * The persistent class for the TA_CONTAINER database table.
 * 
 */
@Entity
@Table(name="TA_CONTAINER")
@NamedQuery(name="Container.findAll", query="SELECT t FROM Container t")
public class Container extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_CONTAINER_PKCONTAINER_GENERATOR", sequenceName="TA_CONTAINER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_CONTAINER_PKCONTAINER_GENERATOR")
	@Column(name="PK_CONTAINER")
	private Long id;

	@Column(name="NU_HSPACE")
	private BigDecimal nuHspace;

	@Column(name="NU_LEFT")
	private BigDecimal nuLeft;

	@Column(name="NU_LENGTH")
	private BigDecimal nuLength;

	@Column(name="NU_POSITION")
	private BigDecimal nuPosition;

	@Column(name="NU_TOP")
	private BigDecimal nuTop;

	@Column(name="NU_VSPACE")
	private BigDecimal nuVspace;

	@Column(name="NU_WIDTH")
	private BigDecimal nuWidth;

	//bi-directional many-to-one association to TbPage
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PK_PAGE")
	private Page tbPage;

	//bi-directional many-to-one association to TbViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PK_VIEWER")
	private Viewer tbViewer;

	public Container() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getNuHspace() {
		return this.nuHspace;
	}

	public void setNuHspace(BigDecimal nuHspace) {
		this.nuHspace = nuHspace;
	}

	public BigDecimal getNuLeft() {
		return this.nuLeft;
	}

	public void setNuLeft(BigDecimal nuLeft) {
		this.nuLeft = nuLeft;
	}

	public BigDecimal getNuLength() {
		return this.nuLength;
	}

	public void setNuLength(BigDecimal nuLength) {
		this.nuLength = nuLength;
	}

	public BigDecimal getNuPosition() {
		return this.nuPosition;
	}

	public void setNuPosition(BigDecimal nuPosition) {
		this.nuPosition = nuPosition;
	}

	public BigDecimal getNuTop() {
		return this.nuTop;
	}

	public void setNuTop(BigDecimal nuTop) {
		this.nuTop = nuTop;
	}

	public BigDecimal getNuVspace() {
		return this.nuVspace;
	}

	public void setNuVspace(BigDecimal nuVspace) {
		this.nuVspace = nuVspace;
	}

	public BigDecimal getNuWidth() {
		return this.nuWidth;
	}

	public void setNuWidth(BigDecimal nuWidth) {
		this.nuWidth = nuWidth;
	}

	public Page getTbPage() {
		return this.tbPage;
	}

	public void setTbPage(Page tbPage) {
		this.tbPage = tbPage;
	}

	public Viewer getTbViewer() {
		return this.tbViewer;
	}

	public void setTbViewer(Viewer tbViewer) {
		this.tbViewer = tbViewer;
	}

}