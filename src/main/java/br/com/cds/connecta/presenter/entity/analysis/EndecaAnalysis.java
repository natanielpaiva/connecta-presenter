package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the TB_ENDECA_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_ENDECA_ANALYSIS")
public class EndecaAnalysis extends Analysis {

    private static final long serialVersionUID = 1L;

    @Column(name = "TXT_DATA_DOMAIN")
    private String dataDomain;

    @Column(name = "TXT_QUERY")
    private String query;

    public String getDataDomain() {
        return dataDomain;
    }

    public void setDataDomain(String dataDomain) {
        this.dataDomain = dataDomain;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
}
