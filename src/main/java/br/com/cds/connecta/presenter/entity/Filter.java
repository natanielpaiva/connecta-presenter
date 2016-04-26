package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_FILTER")
public class Filter extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_FILTER")
    private Long id;

    @Column(name = "NM_FILTER")
    private String name;

    @ManyToOne
    @JoinColumn(name = "FK_ANALYSIS_COLUMNS")
    private AnalysisColumn analysisColumn;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnalysisColumn getAnalysisColumn() {
        return analysisColumn;
    }

    public void setAnalysisColumn(AnalysisColumn analysisColumn) {
        this.analysisColumn = analysisColumn;
    }

}
