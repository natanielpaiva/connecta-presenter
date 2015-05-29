package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IViewerAS;
import br.com.cds.connecta.presenter.entity.Viewer;
import br.com.cds.connecta.presenter.persistence.IViewerDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.DataContextFactory;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class ViewerAS extends AbstractBaseAS<Viewer> implements IViewerAS {

    @Autowired
    private IViewerDAO viewerDao;

    @Override
    public Viewer get(Long id) {
        return viewerDao.get(id);
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void teste() throws SQLException {
        DataContext dataContext = DataContextFactory.createJdbcDataContext(getConnection());
        
        DataSet dataSet = dataContext.query()
                .from("TB_DATABASE_DS").select("TXT_SCHEMA").execute();
        
        List<Row> row = dataSet.toRows();
        row.get(0).getValues();
        
    }

    public Connection getConnection() throws SQLException {
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewerAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.3.14:1521:cdsdev", "presenter2", "cds312");
        
        return conn;
    }

    @Override
    public List<Viewer> list() throws Exception {
        return viewerDao.list();
    }

    @Override
    public Viewer saveOrUpdate(Viewer entity) throws Exception {
        return viewerDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Long id) throws Exception {
        viewerDao.delete(id);
    }

    @Override
    public void delete(Viewer entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
