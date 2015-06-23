package br.com.cds.connecta.presenter.business.applicationService.dataExtractor.impl;

import br.com.cds.connecta.presenter.bean.analysisviewer.AnalysisViewerResult;
import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataExtractorAS;
import br.com.cds.connecta.presenter.business.applicationService.impl.ViewerAS;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.AnalysisViewer;
import br.com.cds.connecta.presenter.entity.AnalysisVwColumn;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import br.com.cds.connecta.presenter.persistence.impl.DatasourceDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.DataContextFactory;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class DataExtractorAS implements IDataExtractorAS {

    @Autowired
    private DatasourceDAO dataSourceDao;

    @Autowired
    private IAnalysisDAO analysisDao;

    @Override
    public List<Map<String, Object>> getDataProvider(List<AnalysisColumn> analysisColumns) {
        List<Map<String, Object>> dataProvider = null;
        try {
            Analysis analysis = analysisDao.getByIdColumns(analysisColumns.get(0).getId());

            DatabaseDatasource dataBaseDataSource = (DatabaseDatasource) dataSourceDao.findOne(analysis.getDatasource().getId());

            DataContext dataContext = DataContextFactory
                    .createJdbcDataContext(getConnection(dataBaseDataSource));
            dataProvider = getResult(dataContext, analysisColumns);

        } catch (SQLException ex) {
            Logger.getLogger(DataExtractorAS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataProvider;

    }

    @Override
    public AnalysisViewerResult getAnalysisViewerResult(AnalysisViewer analysisViewer) {

        List<Map<String, Object>> dataProvider = getDataProvider(
                getAnalysisColumn(
                        analysisViewer.getAnalysisVwColumn()
                )
        );
        AnalysisViewerResult analysViewerResult = new AnalysisViewerResult();
        analysViewerResult.setResult((List<Object>) (Object) dataProvider);
        analysViewerResult.setAnalysisViewer(analysisViewer);

        return analysViewerResult;
    }

    @Override
    public List<AnalysisColumn> getAnalysisColumn(List<AnalysisVwColumn> analysisVwColumns) {
        List<AnalysisColumn> analysisColumn = new ArrayList<>();

        for (AnalysisVwColumn analysisVwColumn : analysisVwColumns) {
            analysisColumn.add(analysisVwColumn.getAnalysisColumn());
        }
        return analysisColumn;
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

            String teste = "jdbc:oracle:thin:@"
                    + dataBaseDatasource.getServer()
                    + ":"
                    + dataBaseDatasource.getPort()
                    + ":"
                    + dataBaseDatasource.getSid();

            System.out.println(teste);

            conn = DriverManager.getConnection(teste,
                    dataBaseDatasource.getUser(),
                    dataBaseDatasource.getPassword());

        }
        return conn;

    }

    @Override
    public List<Map<String, Object>> getResult(DataContext dataContext, List<AnalysisColumn> analysisColumns) {

        Query q = new Query();

        for (AnalysisColumn analysisColumn : analysisColumns) {
            q.select(analysisColumn.getName());
        }

        String[] table = analysisColumns.get(0).getFormula().split("\\.");

        q.from(table[0]);

        DataSet dataset = dataContext.executeQuery(q);

        List<Map<String, Object>> result = new ArrayList<>();
        for (Row row : dataset) {
            Object[] values = row.getValues();
            Map<String, Object> object = new HashMap<>(analysisColumns.size());
            for (int i = 0; i < values.length; i++) {
                Object value = values[i];
                object.put(analysisColumns.get(i).getLabel(), value);
            }
            result.add(object);

        }
        dataset.close();
        return result;

    }

}
