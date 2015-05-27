/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.database.DatabaseService;
import br.com.cds.connecta.framework.connector.database.service.IDatabaseColumn;
import br.com.cds.connecta.framework.connector.database.service.IDatabaseTable;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.entity.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class DatabaseAS implements IDatabaseAS {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List getTables(Long id) {

        DatabaseDatasource datasource = em.find(DatabaseDatasource.class, id);
        DatabaseService database = new DatabaseService();
        ArrayList<AnalysisColumn> columns = new ArrayList<>();

        List<IDatabaseTable> databaseTables = database.getTables(datasource.getServer(),
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

    @Override
    public List getSqlColumn(Long id) {
        
       //fazendo
       DatabaseDatasource datasource = em.find(DatabaseDatasource.class, id);
       DatabaseService database = new DatabaseService();
       
        return null;
        
    }
}

