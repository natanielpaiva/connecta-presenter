package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.database.Database;
import br.com.cds.connecta.framework.connector.database.DatabaseService;
import br.com.cds.connecta.framework.connector.database.service.DatabaseTable;
import br.com.cds.connecta.framework.connector.database.service.IDatabaseColumn;
import br.com.cds.connecta.framework.connector.database.service.IDatabaseTable;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.business.strategy.connector.DataBaseConnectorStrategy;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.metamodel.DataContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class DatabaseAS implements IDatabaseAS {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DatasourceRepository dataSourceDao;

    @Autowired
    private DataBaseConnectorStrategy dataBaseConnectorStrategy;

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
    public List getSqlColumn(Long id) {

        //fazendo
        DatabaseDatasource datasource = em.find(DatabaseDatasource.class, id);
        DatabaseService database = new DatabaseService();

        return null;

    }


    @Override
    public List<Map<String, Object>> getDataSql(Long id, DatabaseAnalysis databaseAnalysis) {
        List<Map<String, Object>> dataProvider;
        List<ConnectorColumn> connectorColumn = new ArrayList<>();

        List<AnalysisColumn> analysisColumns = databaseAnalysis.getAnalysisColumns();

        if (analysisColumns != null) {
            for (AnalysisColumn analysisColumn : analysisColumns) {
                ConnectorColumn column = new ConnectorColumn();
                column.setId(analysisColumn.getId());
                column.setLabel(analysisColumn.getLabel());
                column.setName(analysisColumn.getName());
                column.setFormula(analysisColumn.getFormula());
                connectorColumn.add(column);
            }
         dataProvider = dataBaseConnectorStrategy.getDataProvider(databaseAnalysis, connectorColumn);
        }else{
             DataContext dataContext;
                Database database = new Database();
                DatabaseDatasource datasource = (DatabaseDatasource) dataSourceDao.findOne(id);
                
                dataContext = database.getDados(
                datasource.getDriver().toString(),
                datasource.getServer(),
                datasource.getPort().toString(),
                datasource.getSid(),
                datasource.getUser(),
                datasource.getPassword());
            
         dataProvider= database.getResultSql(dataContext, databaseAnalysis.getSql());
        }

        return dataProvider;
    }

}
