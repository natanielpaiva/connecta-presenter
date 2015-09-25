package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public interface ConnectorStrategy {
    
    List<Map<String, Object>>getDataProvider(Analysis analysis, List<ConnectorColumn> columns);
    
}
