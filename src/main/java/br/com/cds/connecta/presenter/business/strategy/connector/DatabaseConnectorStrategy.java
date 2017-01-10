package br.com.cds.connecta.presenter.business.strategy.connector;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.context.database.ConnectorDriver;
import br.com.cds.connecta.framework.connector2.context.database.DatabaseDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.database.mysql.MySQLDriver;
import br.com.cds.connecta.framework.connector2.context.database.oracle.OracleDriver;
import br.com.cds.connecta.framework.connector2.context.database.orientdb.OrientdbDriver;
import br.com.cds.connecta.framework.connector2.context.database.postgresql.PostgresqlDriver;
import br.com.cds.connecta.framework.connector2.context.database.sqlserver.SqlServerDriver;
import br.com.cds.connecta.framework.connector2.query.QueryBuilder;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisAS;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import br.com.cds.connecta.presenter.domain.DatabaseRequestTypeEnum;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;

/**
 *
 * @author diego
 */
@Service
public class DatabaseConnectorStrategy extends AbstractConnectorStrategy {

    @Autowired
    private DatasourceRepository repository;

    @Autowired
    private IAnalysisAS analysisService;

    @Autowired
    private IDatabaseAS service;

    protected Request makeRequest(AnalysisExecuteRequest analysisExecuteRequest) throws SQLException {
        DatabaseAnalysis databaseAnalysis = (DatabaseAnalysis) analysisExecuteRequest.getAnalysis();
        DatabaseDatasource datasource = (DatabaseDatasource) repository.findOne(databaseAnalysis.getDatasource().getId());

        ConnectorDriver driver = service.makeConnectorDriver(datasource);

        DatabaseDataContextFactory dataContextFactory;
        QueryBuilder query;

        if (DatabaseRequestTypeEnum.TABLE.equals(databaseAnalysis.getRequestType())) {
            dataContextFactory
                    = new DatabaseDataContextFactory(driver, databaseAnalysis.getTable(),
                            datasource.getUser(), datasource.getPassword());

            query = new QueryBuilder()
                    //.setSchema(datasource.getSchema())
                    //.setTable(databaseAnalysis.getTable())
                    .setColumns(
                            toConnectorColumns(databaseAnalysis.getAnalysisColumns())
                    );

        } else {  // if (DatabaseRequestTypeEnum.SQL.equals(databaseAnalysis.getRequestType()))
            dataContextFactory
                    = new DatabaseDataContextFactory(databaseAnalysis.getSql(),
                            driver, datasource.getUser(), datasource.getPassword(),
                            analysisExecuteRequest.isUpdatingCache());

            verifyIfIsCached(databaseAnalysis, dataContextFactory);

            query = new QueryBuilder().setColumns(
                    toConnectorColumns(databaseAnalysis.getAnalysisColumns())
            );
        }

        addPaginationIfDefined(query, analysisExecuteRequest);
        addDrillIfDefined(query, analysisExecuteRequest, dataContextFactory);
        addFiltersIfDefined(query, analysisExecuteRequest, dataContextFactory);
        Request request = new Request(dataContextFactory, query);

        return request;
    }

    private void verifyIfIsCached(DatabaseAnalysis databaseAnalysis,
            DatabaseDataContextFactory dataContextFactory) {
        if (!databaseAnalysis.isCached() && databaseAnalysis.getId() != null) {
            if (dataContextFactory.isCached()) {
                databaseAnalysis.setCached(true);
            	analysisService.saveOrUpdate(databaseAnalysis);
            }
        }
    }

    public ConnectorDriver makeConnectorDriver(DatabaseDatasource datasource) {
        ConnectorDriver driver = null;

        if (DatabaseDatasourceDriverEnum.ORACLE_SID.equals(datasource.getDriver())) {
            driver = new OracleDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSid());
        }
        if (DatabaseDatasourceDriverEnum.MYSQL.equals(datasource.getDriver())) {
            driver = new MySQLDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSchema());
        }
        if (DatabaseDatasourceDriverEnum.POSTGRESQL.equals(datasource.getDriver())) {
            driver = new PostgresqlDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSchema());
        }

        if (DatabaseDatasourceDriverEnum.SQLSERVER.equals(datasource.getDriver())) {
            driver = new SqlServerDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSchema());
        }

        if (DatabaseDatasourceDriverEnum.ORIENTDB.equals(datasource.getDriver())) {
            driver = new OrientdbDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSchema());
        }
        return driver;
    }

}
