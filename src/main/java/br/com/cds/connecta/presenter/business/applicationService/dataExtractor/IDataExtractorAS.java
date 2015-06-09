package br.com.cds.connecta.presenter.business.applicationService.dataExtractor;

import br.com.cds.connecta.presenter.bean.analysisviewer.AnalysisViewerResult;
import br.com.cds.connecta.presenter.entity.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.AnalysisViewer;
import br.com.cds.connecta.presenter.entity.AnalysisVwColumn;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.metamodel.DataContext;

/**
 *
 * @author Nataniel Paiva
 */
public interface IDataExtractorAS {

    Connection getConnection(DatabaseDatasource dataSource) throws SQLException;

    List<Map<String, Object>> getResult(DataContext dataContext, List<AnalysisColumn> analysisColumns);

    List<Map<String, Object>> getDataProvider(List<AnalysisColumn> analysisColumns);

    List<AnalysisColumn> getAnalysisColumn(List<AnalysisVwColumn> analysisVwColumns);
    
    AnalysisViewerResult getAnalysisViewerResult( AnalysisViewer analysisViewer );

}
