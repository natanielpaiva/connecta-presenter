package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector.database.Database;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import java.util.List;
import java.util.Map;
import org.apache.metamodel.DataContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class DataBaseConnectorStrategy implements ConnectorStrategy {

    @Autowired
    private DatasourceRepository dataSourceDao;
    
    @Override
    public List<Map<String, Object>> getDataProvider(Analysis analysis, List<ConnectorColumn> columns) {
        
        DatabaseDatasource dataBaseDataSource = (DatabaseDatasource) dataSourceDao.findOne(analysis.getDatasource().getId());
        
        List<Map<String, Object>> dataProvider = null;
        DataContext dataContext;
        
        Database database = new Database();

        DatabaseAnalysis databaseAnalysis = (DatabaseAnalysis)analysis;
        
         dataContext = database.getDados(
                    dataBaseDataSource.getDriver().toString(),
                    dataBaseDataSource.getServer(),
                    dataBaseDataSource.getPort().toString(),
                    dataBaseDataSource.getSid(),
                    dataBaseDataSource.getUser(),
                    dataBaseDataSource.getPassword());      
        
        
       if(databaseAnalysis.getTable() != null){
           dataProvider = database.getResult(dataContext, columns);
       }
       
       if(databaseAnalysis.getSql() != null){
           dataProvider =  database.getResultSql(dataContext, columns, databaseAnalysis.getSql());
       }
       
        
        
        return dataProvider;

    }
    
}
