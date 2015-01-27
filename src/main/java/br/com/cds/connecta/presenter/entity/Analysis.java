package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.NamedQueries;

/**
 * The persistent class for the TB_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_ANALYSIS")
@NamedQueries({
    @NamedQuery(name = "Analysis.findAll", query = "SELECT t FROM Analysis t")
})
public class Analysis extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_ANALYSIS_PKANALYSIS_GENERATOR", sequenceName = "TB_ANALYSIS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_ANALYSIS_PKANALYSIS_GENERATOR")
    @Column(name = "PK_ANALYSIS")
    private Long id;

    @Column(name = "DS_ANALYSIS")
    private String dsAnalysis;

    @Column(name = "NM_ANALYSIS")
    private String nmAnalysis;

    @Column(name = "TP_ANALYSIS")
    private String tpAnalysis;

    //bi-directional many-to-one association to TbDatasource
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_DATASOURCE")
    private Datasource tbDatasource;

    public Analysis() {
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsAnalysis() {
        return this.dsAnalysis;
    }

    public void setDsAnalysis(String dsAnalysis) {
        this.dsAnalysis = dsAnalysis;
    }

    public String getNmAnalysis() {
        return this.nmAnalysis;
    }

    public void setNmAnalysis(String nmAnalysis) {
        this.nmAnalysis = nmAnalysis;
    }

    public String getTpAnalysis() {
        return this.tpAnalysis;
    }

    public void setTpAnalysis(String tpAnalysis) {
        this.tpAnalysis = tpAnalysis;
    }

    public Datasource getTbDatasource() {
        return this.tbDatasource;
    }

    public void setTbDatasource(Datasource tbDatasource) {
        this.tbDatasource = tbDatasource;
    }
}
