package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the TB_DB_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_DB_ANALYSIS")
public class DatabaseAnalysis extends Analysis {

    private static final long serialVersionUID = 1L;

    @Column(name = "TXT_FIELDS")
    private String fields;

    @Column(name = "TXT_FROM")
    private String from;

    @Column(name = "TXT_WHERE")
    private String where;

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

}
