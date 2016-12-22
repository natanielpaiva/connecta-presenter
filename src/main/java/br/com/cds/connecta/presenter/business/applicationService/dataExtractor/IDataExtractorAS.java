package br.com.cds.connecta.presenter.business.applicationService.dataExtractor;

import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nataniel Paiva
 */
public interface IDataExtractorAS {
    
//    AnalysisViewerResult getAnalysisViewerResult( AnalysisViewer analysisViewer );
//    
//    List<AnalysisColumn> getAnalysisColumn(List<AnalysisViewerColumn> analysisVwColumns);
//    
//    List<ConnectorColumn> getConnectorColumn(List<AnalysisViewerColumn> analysisVwColumns);
//
//    List<Map<String, Object>> getDataProvider(AnalysisViewer analysisViewer);
    
    /**
     * Executa a análise de acordo com a especificação informada no AnalysisExecuteRequest
     * 
     * @param analysisExecuteRequest Especificação da requisição de análise
     * @return Resultado "tabular" da análise informada
     */
    List<Map<String, Object>> executeAnalysis(AnalysisExecuteRequest analysisExecuteRequest) throws SQLException ;

    /**
     * Lista os possíveis valores aplicáveis para o filtro com o ID informado
     * 
     * @param analysisExecuteRequest Especificação da requisição de análise
     * @param column ID do Filtro ou nome da coluna
     * @return Lista de possíveis valores a serem aplicados
     */
    List<Object> possibleValuesFor(AnalysisExecuteRequest analysisExecuteRequest, Object column) throws SQLException ;

}
