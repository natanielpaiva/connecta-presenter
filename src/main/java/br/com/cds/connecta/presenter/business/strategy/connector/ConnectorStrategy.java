package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public interface ConnectorStrategy {
    
    List<Map<String, Object>> getDataProvider(AnalysisExecuteRequest analysisExecuteRequest);
    
}
