package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the TB_BI_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_BI_ANALYSIS")
public class BIAnalysis extends Analysis {

    private static final long serialVersionUID = 1L;

    @Column(name = "TXT_ANALYSIS")
    private String analysis;

    @Column(name = "TXT_CATALOG")
    private String catalog;

    @Column(name = "TXT_FIELDS")
    private String fields;

    @Column(name = "TXT_FROM")
    private String from;

    @Column(name = "TXT_WHERE")
    private String where;

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

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
