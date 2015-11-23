package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the TB_HDFS_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_HDFS_ANALYSIS")
public class HdfsAnalysis extends Analysis {

    private static final long serialVersionUID = 1L;

    @Column(name = "TXT_HIVEQL")
    private String hiveQL;

    @Column(name = "TXT_MR_CLASS")
    private String mapReduceClass;

    @Column(name = "TXT_MR_METHOD")
    private String mapReduceMethod;

    @Column(name = "TXT_PIG_QUERY")
    private String pigQuery;

    public String getHiveQL() {
        return hiveQL;
    }

    public void setHiveQL(String hiveQL) {
        this.hiveQL = hiveQL;
    }

    public String getMapReduceClass() {
        return mapReduceClass;
    }

    public void setMapReduceClass(String mapReduceClass) {
        this.mapReduceClass = mapReduceClass;
    }

    public String getMapReduceMethod() {
        return mapReduceMethod;
    }

    public void setMapReduceMethod(String mapReduceMethod) {
        this.mapReduceMethod = mapReduceMethod;
    }

    public String getPigQuery() {
        return pigQuery;
    }

    public void setPigQuery(String pigQuery) {
        this.pigQuery = pigQuery;
    }

}
