package br.com.cds.connecta.presenter.business.applicationService.dataExtractor;

import br.com.cds.connecta.presenter.entity.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;

/**
 *
 * @author Nataniel Paiva
 */
public interface IDataExtractorAS {

    Connection getConnection(DatabaseDatasource dataSource) throws SQLException;
    
    DataSet getDataSet(DataContext dataContext, List<AnalysisColumn> analysisColumns);

    List<Object[]> getResult(Long id);

}
