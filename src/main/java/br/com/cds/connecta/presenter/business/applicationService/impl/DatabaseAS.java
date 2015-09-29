package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.database.DatabaseService;
import br.com.cds.connecta.framework.connector.database.service.IDatabaseColumn;
import br.com.cds.connecta.framework.connector.database.service.IDatabaseTable;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
                column.setFormula(table.getTableName()+"."+tableColumn.getName());
                columns.add(column);
            }
        }
        return databaseTables;
    }
    
    
    private String getParamsConnection(DatabaseDatasource dataBaseDataSource){
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
}

