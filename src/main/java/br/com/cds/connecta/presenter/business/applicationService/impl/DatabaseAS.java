package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.database.DatabaseService;
import br.com.cds.connecta.framework.connector.database.service.IDatabaseColumn;
import br.com.cds.connecta.framework.connector.database.service.IDatabaseTable;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.business.strategy.connector.DatabaseConnectorStrategy;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class DatabaseAS implements IDatabaseAS {

    @Autowired
    private DatasourceRepository dataSourceDao;

    @Autowired
    private DatabaseConnectorStrategy dataBaseConnectorStrategy;
    
    private final Logger logger = Logger.getLogger(DatabaseAS.class);

    @Override
    public List getTables(Long id) {

        DatabaseDatasource datasource = (DatabaseDatasource) dataSourceDao.findOne(id);

        DatabaseService database = new DatabaseService();
        ArrayList<AnalysisColumn> columns = new ArrayList<>();

        List<IDatabaseTable> databaseTables = database.getTables(getParamsConnection(datasource),
                datasource.getSchema(),
                datasource.getUser(),
                datasource.getPassword());

        for (IDatabaseTable table : databaseTables) {
            List<IDatabaseColumn> tableColumns = table.getColumns();
            for (IDatabaseColumn tableColumn : tableColumns) {
                AnalysisColumn column = new AnalysisColumn();
                column.setName(tableColumn.getName());
                column.setFormula(table.getTableName() + "." + tableColumn.getName());
                columns.add(column);
            }
        }
        
        return databaseTables;
    }

    private String getParamsConnection(DatabaseDatasource dataBaseDataSource) {
        return "jdbc:oracle:thin:@"
                + dataBaseDataSource.getServer()
                + ":"
                + dataBaseDataSource.getPort()
                + ":"
                + dataBaseDataSource.getSid();
    }

    @Override
    public List<Map<String, Object>> getDataSql(Long id, DatabaseAnalysis databaseAnalysis) {
        return dataBaseConnectorStrategy.getDataProvider(databaseAnalysis, null);
    }

}
