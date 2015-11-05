package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public interface IDatabaseAS {

    List<AnalysisColumn> getTables(Long id);
    List getSqlColumn(Long id);
    List<Map<String, Object>>  getDataSql(Long id, DatabaseAnalysis databaseAnalysis);

}
