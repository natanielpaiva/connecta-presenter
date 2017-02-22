
package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.presenter.domain.DatabaseRequestTypeEnum;
import br.com.cds.connecta.presenter.domain.Wso2SearchTypeEnum;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "TB_WSO2_ANALYSIS")
public class Wso2Analysis extends Analysis {

    private static final long serialVersionUID = 1L;

    
    @Column(name = "TXT_TABLE")
    @Type(type = "text")
    private String table;
    
    @Column(name = "TXT_QUERY")
    @Type(type = "text")
    private String query;
    
    @Column(name = "TP_WSO2_ANALYSIS")
    @Enumerated(EnumType.STRING)
    private Wso2SearchTypeEnum searchType;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FROM")
    private Date from;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_TO")
    private Date to;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Wso2SearchTypeEnum getSearchType() {
        return searchType;
    }

    public void setSearchType(Wso2SearchTypeEnum searchType) {
        this.searchType = searchType;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
    
}
