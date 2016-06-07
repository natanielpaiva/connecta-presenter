package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.presenter.domain.DatabaseRequestTypeEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * The persistent class for the TB_DB_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_DB_ANALYSIS")
@NamedQuery(name = "DatabaseAnalysis.get",
        query = "SELECT a FROM DatabaseAnalysis a WHERE a.id = :id")
public class DatabaseAnalysis extends Analysis {

    private static final long serialVersionUID = 1L;

    @Column(name = "TXT_TABLE")
    private String table;
    
    @Column(name = "TXT_SQL")
    @Type(type = "text")
    private String sql;
    
    @Column(name = "TP_DB_ANALYSIS")
    @Enumerated(EnumType.STRING)
    private DatabaseRequestTypeEnum requestType;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
    
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public DatabaseRequestTypeEnum getRequestType() {
        return requestType;
    }

    public void setRequestType(DatabaseRequestTypeEnum requestType) {
        this.requestType = requestType;
    }

}
