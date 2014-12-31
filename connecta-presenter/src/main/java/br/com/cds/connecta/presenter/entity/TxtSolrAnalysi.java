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
 * The persistent class for the TXT_SOLR_ANALYSIS database table.
 * 
 */
@Entity
@Table(name="TXT_SOLR_ANALYSIS")
@NamedQuery(name="TxtSolrAnalysi.findAll", query="SELECT t FROM TxtSolrAnalysi t")
public class TxtSolrAnalysi extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TXT_SOLR_ANALYSIS_PKSOLRANALYSIS_GENERATOR", sequenceName="TXT_SOLR_ANALYSIS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TXT_SOLR_ANALYSIS_PKSOLRANALYSIS_GENERATOR")
	@Column(name="PK_SOLR_ANALYSIS")
	private Long id;

	@Column(name="TXT_FACET")
	private String txtFacet;

	@Column(name="TXT_QUERY")
	private String txtQuery;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS")
	private Analysis tbAnalysi;

	public TxtSolrAnalysi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxtFacet() {
		return this.txtFacet;
	}

	public void setTxtFacet(String txtFacet) {
		this.txtFacet = txtFacet;
	}

	public String getTxtQuery() {
		return this.txtQuery;
	}

	public void setTxtQuery(String txtQuery) {
		this.txtQuery = txtQuery;
	}

	public Analysis getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysis tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

}