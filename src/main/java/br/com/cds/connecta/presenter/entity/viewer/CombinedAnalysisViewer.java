package br.com.cds.connecta.presenter.entity.viewer;

import br.com.cds.connecta.presenter.entity.CombinedAnalysis;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the TB_CMB_VIEWER database table.
 *
 */
@Entity
@Table(name = "TB_CMB_VIEWER")
public class CombinedAnalysisViewer extends Viewer {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ANALYSIS")
    private Analysis analysis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_COMBINED_ANALYSIS")
    private CombinedAnalysis combinedAnalysis;

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public CombinedAnalysis getCombinedAnalysis() {
        return combinedAnalysis;
    }

    public void setCombinedAnalysis(CombinedAnalysis combinedAnalysis) {
        this.combinedAnalysis = combinedAnalysis;
    }
}
