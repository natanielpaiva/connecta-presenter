package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
@Entity
@Table(name = "TB_ANALYSIS_RELATION")
public class AnalysisRelation extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ANALYSIS_RELATION")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_LEFT_ANALYSIS_COLUMN")
    private AnalysisColumn leftAnalysisColumn;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_RIGHT_ANALYSIS")
    @JsonIgnoreProperties("analysisRelations")
    private Analysis rightAnalysis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_RIGHT_ANALYSIS_COLUMN")
    private AnalysisColumn rightAnalysisColumn;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Analysis getRightAnalysis() {
        return rightAnalysis;
    }

    public void setRightAnalysis(Analysis rightAnalysis) {
        this.rightAnalysis = rightAnalysis;
    }

    public AnalysisColumn getLeftAnalysisColumn() {
        return leftAnalysisColumn;
    }

    public void setLeftAnalysisColumn(AnalysisColumn leftAnalysisColumn) {
        this.leftAnalysisColumn = leftAnalysisColumn;
    }

    public AnalysisColumn getRightAnalysisColumn() {
        return rightAnalysisColumn;
    }

    public void setRightAnalysisColumn(AnalysisColumn rightAnalysisColumn) {
        this.rightAnalysisColumn = rightAnalysisColumn;
    }

}
