package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public interface ConnectorStrategy {
    
    /**
     * Monta uma requisição específica para o FusionClient e retorna os resultados da análise
     * 
     * @param analysisExecuteRequest A requisição de execução da Análise
     * @return 
     */
    List<Map<String, Object>> getDataProvider(AnalysisExecuteRequest analysisExecuteRequest);

    /**
     * Lista os possíveis valores para um filtro específico definido para uma análise
     * 
     * @param analysisExecuteRequest A requisição de execução da Análise
     * @param filter A coluna que irá realizar o filtro
     * @return 
     */
    List<Object> possibleValuesFor(AnalysisExecuteRequest analysisExecuteRequest, String filter);
    
}
