package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.framework.connector2.domain.DatabaseRequestTypeEnum;
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

//    @Column(name = "TXT_FIELDS")
//    private String fields;
//
//    @Column(name = "TXT_FROM")
//    private String from;
//
//    @Column(name = "TXT_WHERE")
//    private String where;

    @Column(name = "TXT_TABLE")
    private String table;
    
    @Column(name = "TXT_SQL")
    @Type(type = "text")
    private String sql;
    
    @Column(name = "TP_DB_ANALYSIS")
    @Enumerated(EnumType.STRING)
    private DatabaseRequestTypeEnum requestType;

//    public String getFields() {
//        return fields;
//    }
//
//    public void setFields(String fields) {
//        this.fields = fields;
//    }
//
//    public String getFrom() {
//        return from;
//    }
//
//    public void setFrom(String from) {
//        this.from = from;
//    }
//
//    public String getWhere() {
//        return where;
//    }
//
//    public void setWhere(String where) {
//        this.where = where;
//    }

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
