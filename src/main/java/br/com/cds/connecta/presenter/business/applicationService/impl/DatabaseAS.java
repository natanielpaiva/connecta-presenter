package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.connector.database.DatabaseService;
import br.com.cds.connecta.framework.connector2.context.database.ConnectorDriver;
import br.com.cds.connecta.framework.connector2.context.database.DatabaseDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.database.mysql.MySQLDriver;
import br.com.cds.connecta.framework.connector2.context.database.oracle.OracleDriver;
import br.com.cds.connecta.framework.connector2.context.database.orientdb.OrientdbDriver;
import br.com.cds.connecta.framework.connector2.context.database.postgresql.PostgresqlDriver;
import br.com.cds.connecta.framework.connector2.context.database.sqlserver.SqlServerDriver;
import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.business.strategy.connector.DatabaseConnectorStrategy;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;

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
    public List getTables(Long id) throws SQLException {

        DatabaseDatasource datasource = (DatabaseDatasource) dataSourceDao.findOne(id);

        ConnectorDriver makeConnectorDriver = dataBaseConnectorStrategy.makeConnectorDriver(datasource);

        DatabaseDataContextFactory contextFactory = new DatabaseDataContextFactory(
                makeConnectorDriver, datasource.getUser(), datasource.getPassword());

        return contextFactory.getTablesColumns();

    }

    @Override
    public void testConnection(DatabaseDatasource datasource) throws SQLException {
        DatabaseService database = new DatabaseService();

        Connection conn = database.testConnection(
                getParamsConnection(datasource),
                datasource.getUser(),
                datasource.getPassword());

        if (Util.isNotNull(conn)) {
            conn.close();
        }
    }

    private String getParamsConnection(DatabaseDatasource databaseDatasource) {
        ConnectorDriver driver = makeConnectorDriver(databaseDatasource);

        return driver.jdbcUrl();
    }

    @Override
    public ConnectorDriver makeConnectorDriver(DatabaseDatasource datasource) {
        ConnectorDriver driver = null;
        if (DatabaseDatasourceDriverEnum.ORACLE_SID.equals(datasource.getDriver())) {
            driver = new OracleDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSid());
        } else if (DatabaseDatasourceDriverEnum.MYSQL.equals(datasource.getDriver())) {
            driver = new MySQLDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSchema());
        } else if (DatabaseDatasourceDriverEnum.POSTGRESQL.equals(datasource.getDriver())) {
            driver = new PostgresqlDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSchema());
        } else if (DatabaseDatasourceDriverEnum.SQLSERVER.equals(datasource.getDriver())) {
            driver = new SqlServerDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSchema());
        }else if (DatabaseDatasourceDriverEnum.ORIENTDB.equals(datasource.getDriver())) {
            driver = new OrientdbDriver(datasource.getServer(), datasource.getPort().toString(), datasource.getSchema());
        }

        return driver;
    }

//    @Override
//    public List<Map<String, Object>> getDataSql(AnalysisExecuteRequest analysisExecuteRequest) {
//        return dataBaseConnectorStrategy.getDataProvider(analysisExecuteRequest);
//    }
}
