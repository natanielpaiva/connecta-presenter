package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;


/**
 * The persistent class for the TB_SOLR_ANALYSIS database table.
 *
 */
@Entity
@DynamicUpdate
@Table(name = "TB_SOLR_ANALYSIS")
public class SolrAnalysis extends Analysis {

    private static final long serialVersionUID = 1L;

    @Column(name = "TXT_FACET")
    private Long facet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_QUERY")
    private Query query;
    
    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Long getFacet() {
        return facet;
    }

    public void setFacet(Long facet) {
        this.facet = facet;
    }
    
}
