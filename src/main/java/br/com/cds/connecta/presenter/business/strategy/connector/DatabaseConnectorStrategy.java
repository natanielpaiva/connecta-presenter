package br.com.cds.connecta.presenter.business.strategy.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.context.database.ConnectorDriver;
import br.com.cds.connecta.framework.connector2.context.database.DatabaseDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.database.mysql.MySQLDriver;
import br.com.cds.connecta.framework.connector2.context.database.oracle.OracleDriver;
import br.com.cds.connecta.framework.connector2.domain.DatabaseRequestTypeEnum;
import br.com.cds.connecta.framework.connector2.query.QueryBuilder;
import static br.com.cds.connecta.framework.core.util.Util.isNotNull;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
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
    private IDatabaseAS service;

    protected Request makeRequest(AnalysisExecuteRequest analysisExecuteRequest) {
        DatabaseAnalysis databaseAnalysis = (DatabaseAnalysis) analysisExecuteRequest.getAnalysis();
        DatabaseDatasource datasource = (DatabaseDatasource) repository.findOne(databaseAnalysis.getDatasource().getId());
        
        ConnectorDriver driver = service.makeConnectorDriver(datasource);
        
        DatabaseDataContextFactory dataContextFactory;
        QueryBuilder query;
        
        if (DatabaseRequestTypeEnum.TABLE.equals(databaseAnalysis.getRequestType())) {
            dataContextFactory = new DatabaseDataContextFactory(driver, databaseAnalysis.getTable(), datasource.getUser(), datasource.getPassword());
            query = new QueryBuilder()
                    .setSchema(datasource.getSchema())
                    .setTable(databaseAnalysis.getTable())
                    .setColumns(
                            toConnectorColumns( databaseAnalysis.getAnalysisColumns() )
                    )
                    ;
        } else {  // if (DatabaseRequestTypeEnum.SQL.equals(databaseAnalysis.getRequestType()))
            dataContextFactory = new DatabaseDataContextFactory(databaseAnalysis.getSql(), driver, datasource.getUser(), datasource.getPassword());
            
            query = new QueryBuilder().setColumns(
                    toConnectorColumns( databaseAnalysis.getAnalysisColumns() )
            );
        }
        
        addPaginationIfDefined(query, analysisExecuteRequest);
        addDrillIfDefined(query, analysisExecuteRequest, dataContextFactory);
        addFiltersIfDefined(query, analysisExecuteRequest, dataContextFactory);
        Request request = new Request(dataContextFactory, query);
        
        return request;
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

    private void addPaginationIfDefined(QueryBuilder query, AnalysisExecuteRequest analysisExecuteRequest) {
        if (isNotNull(analysisExecuteRequest.getPagination()) 
                && isNotNull(analysisExecuteRequest.getPagination().getCount()) && 
                isNotNull(analysisExecuteRequest.getPagination().getPage())) {
            query.setPagination(analysisExecuteRequest.getPagination().getCount(), analysisExecuteRequest.getPagination().getPage());
        }
    }

}
