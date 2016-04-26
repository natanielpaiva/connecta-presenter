package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector2.FusionClient;
import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.common.ConnectorColumn;
import br.com.cds.connecta.framework.connector2.common.QueryBuilder;
import br.com.cds.connecta.framework.connector2.context.database.DatabaseDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.database.ConnectorDriver;
import br.com.cds.connecta.framework.connector2.context.database.mysql.MySQLDriver;
import br.com.cds.connecta.framework.connector2.context.database.oracle.OracleDriver;
import br.com.cds.connecta.framework.connector2.domain.DatabaseRequestTypeEnum;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
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
public class DatabaseConnectorStrategy implements ConnectorStrategy {

    @Autowired
    private DatasourceRepository repository;
    
    @Autowired
    private IDatabaseAS service;

    private final Logger logger = Logger.getLogger(DatabaseConnectorStrategy.class);

    @Override
    public List<Map<String, Object>> getDataProvider(Analysis analysis) {
        List<Map<String, Object>> dataProvider = null;
        FusionClient fusionClient = new FusionClient();
        DatabaseAnalysis databaseAnalysis = (DatabaseAnalysis) analysis;

        DatabaseDatasource datasource = (DatabaseDatasource) repository.findOne(analysis.getDatasource().getId());

        ConnectorDriver driver = service.makeConnectorDriver(datasource);

        if (DatabaseRequestTypeEnum.TABLE.equals(databaseAnalysis.getRequestType())) {
            logger.info(String.format("TABLE ANALYSIS %s.%s", datasource.getSchema(), databaseAnalysis.getTable()));
            DatabaseDataContextFactory dataContextFactory = new DatabaseDataContextFactory(driver, datasource.getUser(), datasource.getPassword());
            
            QueryBuilder query = new QueryBuilder()
                    .setSchema(datasource.getSchema())
                    .setTable(databaseAnalysis.getTable())
                    .setColumns(
                        toConnectorColumns( databaseAnalysis.getAnalysisColumns() )
                    )
                    ;

            Request request = new Request(dataContextFactory, query);
            dataProvider = fusionClient.getAll(request);
            
        } else if (DatabaseRequestTypeEnum.SQL.equals(databaseAnalysis.getRequestType())) {
            
            logger.info("MANUAL SQL ANALYSIS");
            DatabaseDataContextFactory dataContextFactory = new DatabaseDataContextFactory(databaseAnalysis.getSql(), driver, datasource.getUser(), datasource.getPassword());
            
            QueryBuilder query = new QueryBuilder().setColumns(
                toConnectorColumns( databaseAnalysis.getAnalysisColumns() )
            );

            Request request = new Request(dataContextFactory, query);
            dataProvider = fusionClient.getAll(request);
        }

        return dataProvider;
    }
    
    public ConnectorDriver makeConnectorDriver(DatabaseDatasource datasource) {
        ConnectorDriver driver = null;
        if (DatabaseDatasourceDriverEnum.ORACLE_SID.equals(datasource.getDriver())) {
            driver = new OracleDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSid());
        }
        if (DatabaseDatasourceDriverEnum.MYSQL.equals(datasource.getDriver())) {
            driver = new MySQLDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSchema());
        }

        return driver;
    }

    private List<ConnectorColumn> toConnectorColumns(List<AnalysisColumn> analysisColumns) {
        List<ConnectorColumn> connectorColumns = null;
        
        if (analysisColumns != null) {
            connectorColumns = new ArrayList<>();
            for (AnalysisColumn analysisColumn : analysisColumns) {
                ConnectorColumn column = new ConnectorColumn();
                column.setId(analysisColumn.getId());
                column.setLabel(analysisColumn.getLabel());
                column.setName(analysisColumn.getName());
                column.setFormula(analysisColumn.getFormula());

                logger.info("ADDING COLUMN TO ANALYSIS: " + analysisColumn.getName());

                connectorColumns.add(column);
            }
        }
        
        return connectorColumns;
    }
}
