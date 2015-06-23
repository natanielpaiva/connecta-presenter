package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the TXT_SOLR_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TXT_SOLR_ANALYSIS")
public class SolrAnalysis extends Analysis {

    private static final long serialVersionUID = 1L;

    @Column(name = "TXT_FACET")
    private String facet;

    @Column(name = "TXT_QUERY")
    private String query;

    public String getFacet() {
        return facet;
    }

    public void setFacet(String facet) {
        this.facet = facet;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
}
