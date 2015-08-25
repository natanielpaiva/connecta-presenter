package br.com.cds.connecta.presenter.business.applicationService.dataExtractor;

import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.bean.analysisviewer.AnalysisViewerResult;
import br.com.cds.connecta.presenter.entity.AnalysisViewer;
import br.com.cds.connecta.presenter.entity.AnalysisVwColumn;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nataniel Paiva
 */
public interface IDataExtractorAS {
    
    AnalysisViewerResult getAnalysisViewerResult( AnalysisViewer analysisViewer );
    
    List<ConnectorColumn> getAnalysisColumn(List<AnalysisVwColumn> analysisVwColumns);

    List<Map<String, Object>> getDataProvider(AnalysisViewer analysisViewer);

}
