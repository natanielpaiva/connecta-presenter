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
 * The persistent class for the TB_FILTER_SELECTOR database table.
 * 
 */
@Entity
@Table(name="TB_FILTER_SELECTOR")
@NamedQuery(name="FilterSelector.findAll", query="SELECT t FROM FilterSelector t")
public class FilterSelector extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_FILTER_SELECTOR_PKFILTERSELECTOR_GENERATOR", sequenceName="TB_FILTER_SELECTOR_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_FILTER_SELECTOR_PKFILTERSELECTOR_GENERATOR")
	@Column(name="PK_FILTER_SELECTOR")
	private Long id;

	@Column(name="NM_FILTER")
	private String nmFilter;

	@Column(name="TP_ICON")
	private String tpIcon;

	@Column(name="TP_OPERATOR")
	private String tpOperator;


	public FilterSelector() {
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

	public String getTpIcon() {
		return this.tpIcon;
	}

	public void setTpIcon(String tpIcon) {
		this.tpIcon = tpIcon;
	}

	public String getTpOperator() {
		return this.tpOperator;
	}

	public void setTpOperator(String tpOperator) {
		this.tpOperator = tpOperator;
	}

}