package br.com.cds.connecta.presenter.business.applicationService.dataExtractor.impl;

import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataExtractorAS;
import br.com.cds.connecta.presenter.business.applicationService.impl.ViewerAS;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import br.com.cds.connecta.presenter.entity.Analysis;
import br.com.cds.connecta.presenter.entity.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import br.com.cds.connecta.presenter.persistence.impl.DatasourceDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.DataContextFactory;
import org.apache.metamodel.data.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class DataExtractorAS implements IDataExtractorAS {

//    @Autowired
//    private IViewerDAO viewerDao;

    @Autowired
    private DatasourceDAO dataSourceDao;

    @Autowired
    private IAnalysisDAO analysisDao;

    @Override
    public List<Object[]> getResult(Long id) {
        List<Object[]> result = null;
        try {
            Analysis analysis = analysisDao.getByIdColumns(id);
            
            DatabaseDatasource dataBaseDataSource = (DatabaseDatasource) dataSourceDao.findOne(analysis.getDatasource().getId());
            
            DataContext dataContext = DataContextFactory
                    .createJdbcDataContext(getConnection(dataBaseDataSource));
            DataSet dataSet = getDataSet(dataContext, analysis.getAnalysisColumns());
            result = dataSet.toObjectArrays();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataExtractorAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;

    }

    @Override
    public Connection getConnection(DatabaseDatasource dataBaseDatasource) throws SQLException {
        Connection conn = null;
        if (dataBaseDatasource.getDriver().equals(DatabaseDatasourceDriverEnum.ORACLE_SID)) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ViewerAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn = DriverManager.getConnection("jdbc:oracle:thin:@"
                    + dataBaseDatasource.getServer()
                    + ":"
                    + dataBaseDatasource.getPort()
                    + ":"
                    + dataBaseDatasource.getSid(),
                    dataBaseDatasource.getUser(),
                    dataBaseDatasource.getPassword());

        }
        return conn;

    }

    @Override
    public DataSet getDataSet(DataContext dataContext, List<AnalysisColumn> analysisColumns) {

        String select = "";

        for (AnalysisColumn analysisColumn : analysisColumns) {
            select = select + ", " + analysisColumn.getName();
        }

        select = select.substring(0, select.length() - 1);

        String[] table = analysisColumns.get(0).getFormula().split(".");

        DataSet dataSet = dataContext.query().from(table[0]).select(select).execute();

        return dataSet;

    }

}
