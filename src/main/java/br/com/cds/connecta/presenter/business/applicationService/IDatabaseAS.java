package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.framework.connector2.context.database.ConnectorDriver;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author diego
 */
public interface IDatabaseAS {

    List<AnalysisColumn> getTables(Long id)throws SQLException;
    
    void testConnection(DatabaseDatasource datasource) throws SQLException;
    
    ConnectorDriver makeConnectorDriver(DatabaseDatasource datasource);

}
