package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.framework.connector2.context.database.ConnectorDriver;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public interface IDatabaseAS {

    List<AnalysisColumn> getTables(Long id);
    
    List<Map<String, Object>>  getDataSql(DatabaseAnalysis databaseAnalysis);
    
    ConnectorDriver makeConnectorDriver(DatabaseDatasource datasource);

}
