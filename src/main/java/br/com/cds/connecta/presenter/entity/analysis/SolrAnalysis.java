package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.cds.connecta.presenter.domain.SolrRequestTypeEnum;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;

/**
 * The persistent class for the TB_SOLR_ANALYSIS database table.
 *
 */
@Entity
@DynamicUpdate
@Table(name = "TB_SOLR_ANALYSIS")
@SQLDelete(sql = "update TB_SOLR_ANALYSIS set IS_ACTIVE = 0 where PK_ANALYSIS = ?")
@Where(clause = "IS_ACTIVE = 1")
public class SolrAnalysis extends Analysis {

	private static final long serialVersionUID = 1L;

	@Column(name = "TXT_FACET")
	private Long facet;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_QUERY")
	private Query query;

	@Column(name = "TEXT_QUERY", length = 1000)
	private String textQuery;

	@Column(name = "TP_QUERY_SOLR")
	@Enumerated(EnumType.STRING)
	private SolrRequestTypeEnum requestType;

	@Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
	private boolean isActive = true;

	public Long getFacet() {
		return facet;
	}

	public void setFacet(Long facet) {
		this.facet = facet;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getTextQuery() {
		return textQuery;
	}

	public void setTextQuery(String textQuery) {
		this.textQuery = textQuery;
	}

	public SolrRequestTypeEnum getRequestType() {
		return requestType;
	}

	public void setRequestType(SolrRequestTypeEnum requestType) {
		this.requestType = requestType;

	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
