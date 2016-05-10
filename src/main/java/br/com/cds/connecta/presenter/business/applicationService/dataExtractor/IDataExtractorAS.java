package br.com.cds.connecta.presenter.business.applicationService.dataExtractor;

import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.bean.analysisviewer.AnalysisViewerResult;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewerColumn;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nataniel Paiva
 */
public interface IDataExtractorAS {
    
    AnalysisViewerResult getAnalysisViewerResult( AnalysisViewer analysisViewer );
    
    List<AnalysisColumn> getAnalysisColumn(List<AnalysisViewerColumn> analysisVwColumns);
    
    List<ConnectorColumn> getConnectorColumn(List<AnalysisViewerColumn> analysisVwColumns);

    List<Map<String, Object>> getDataProvider(AnalysisViewer analysisViewer);
    
    List<Map<String, Object>> executeAnalysis(AnalysisExecuteRequest analysisExecuteRequest);

}
