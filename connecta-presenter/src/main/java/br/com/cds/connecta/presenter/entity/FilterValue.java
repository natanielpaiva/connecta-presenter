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
 * The persistent class for the TB_FILTER_VALUE database table.
 * 
 */
@Entity
@Table(name="TB_FILTER_VALUE")
@NamedQuery(name="FilterValue.findAll", query="SELECT t FROM FilterValue t")
public class FilterValue extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_FILTER_VALUE_PKFILTERVALUE_GENERATOR", sequenceName="TB_FILTER_VALUE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_FILTER_VALUE_PKFILTERVALUE_GENERATOR")
	@Column(name="PK_FILTER_VALUE")
	private Long id;

	@Column(name="TXT_VALUE")
	private String txtValue;

	@Column(name="TXT_VALUE_LABEL")
	private String txtValueLabel;

	//bi-directional many-to-one association to Filter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_FILTER")
	private Filter filter;

	public FilterValue() {
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

	public String getTxtValueLabel() {
		return this.txtValueLabel;
	}

	public void setTxtValueLabel(String txtValueLabel) {
		this.txtValueLabel = txtValueLabel;
	}

	public Filter getFilter() {
		return this.filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

}