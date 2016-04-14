package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.framework.connector2.FusionClient;
import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.common.QueryContext;
import br.com.cds.connecta.framework.connector2.context.database.DatabaseDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.database.Driver;
import br.com.cds.connecta.framework.connector2.context.database.oracle.OracleConnection;
import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
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

    private final Logger logger = Logger.getLogger(DatabaseConnectorStrategy.class);

    @Override
    public List<Map<String, Object>> getDataProvider(Analysis analysis, List<ConnectorColumn> columns) {
        List<Map<String, Object>> dataProvider;
        FusionClient fusionClient = new FusionClient();
        DatabaseAnalysis databaseAnalysis = (DatabaseAnalysis) analysis;

        DatabaseDatasource datasource = (DatabaseDatasource) repository.findOne(analysis.getDatasource().getId());
        List<br.com.cds.connecta.framework.connector2.common.ConnectorColumn> connectorColumns = new ArrayList<>();

        if (Util.isNotEmpty(columns)) {
            parseConnectorColumns1To2(columns, connectorColumns);
        } else {
            organizeAnalysisColumns(databaseAnalysis, connectorColumns);
        }

        Driver driver = makeConnectionDriver(datasource);

        if (databaseAnalysis.getTable() != null) {
            logger.info(String.format("TABLE ANALYSIS %s . %s", datasource.getSchema(), databaseAnalysis.getTable()));
            DatabaseDataContextFactory dataContextFactory = new DatabaseDataContextFactory(driver, datasource.getUser(), datasource.getPassword());
            // FIXME Fazer o de tabela
            //List cl = dataContextFactory.getColumns();
            
            QueryContext query = new QueryContext()
                    .setSchema(datasource.getSchema())
                    .setTable(databaseAnalysis.getTable())
                    .setConnectorColumns(connectorColumns);

            Request request = new Request(dataContextFactory, query);
            dataProvider = fusionClient.getAll(request);
        } else { // if (databaseAnalysis.getSql() != null)
            logger.info("MANUAL SQL ANALYSIS");
            DatabaseDataContextFactory dataContextFactory = new DatabaseDataContextFactory(databaseAnalysis.getSql(), driver, datasource.getUser(), datasource.getPassword());
            QueryContext query = new QueryContext().setConnectorColumns(connectorColumns);

            Request request = new Request(dataContextFactory, query);
            dataProvider = fusionClient.getAll(request);
        }

        return dataProvider;
    }

    public Driver makeConnectionDriver(DatabaseDatasource datasource) {
        Driver driver = null;
        if (DatabaseDatasourceDriverEnum.ORACLE_SID.equals(datasource.getDriver())) {
            driver = new OracleConnection(datasource.getServer(), datasource.getPort().toString(), datasource.getSid());
        }

        return driver;
    }

    private void organizeAnalysisColumns(DatabaseAnalysis databaseAnalysis, List<br.com.cds.connecta.framework.connector2.common.ConnectorColumn> connectorColumns) {
        if (databaseAnalysis.getAnalysisColumns() != null) {
            for (AnalysisColumn analysisColumn : databaseAnalysis.getAnalysisColumns()) {
                br.com.cds.connecta.framework.connector2.common.ConnectorColumn column = new br.com.cds.connecta.framework.connector2.common.ConnectorColumn();
                column.setId(analysisColumn.getId());
                column.setLabel(analysisColumn.getLabel());
                column.setName(analysisColumn.getName());
                column.setFormula(analysisColumn.getFormula());

                logger.info("ADDING COLUMN TO ANALYSIS: " + analysisColumn.getName());

                connectorColumns.add(column);
            }
        }
    }

    private void parseConnectorColumns1To2(List<ConnectorColumn> columns1, List<br.com.cds.connecta.framework.connector2.common.ConnectorColumn> columns2) {
        for (ConnectorColumn column1 : columns1) {
            br.com.cds.connecta.framework.connector2.common.ConnectorColumn column2 = new br.com.cds.connecta.framework.connector2.common.ConnectorColumn();
            column2.setId(column1.getId());
            column2.setLabel(column1.getLabel());
            column2.setName(column1.getName());
            column2.setFormula(column1.getFormula());

            logger.info("CONVERTING FROM OLD COLUMN TO NEW: " + column1.getName());

            columns2.add(column2);
        }
    }

}
