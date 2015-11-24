package br.com.cds.connecta.presenter.bean.analysisviewer;

import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;
import java.util.List;

/**
 *
 * @author nataniel
 */
public class AnalysisViewerResult {
    
    private AnalysisViewer analysisViewer;
    
    private List<Object> result;

    public AnalysisViewer getAnalysisViewer() {
        return analysisViewer;
    }

    public void setAnalysisViewer(AnalysisViewer analysisViewer) {
        this.analysisViewer = analysisViewer;
    }

    public List<Object> getResult() {
        return result;
    }

    public void setResult(List<Object> result) {
        this.result = result;
    }
    
}
