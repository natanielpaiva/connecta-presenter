package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public interface ISolr {
    
   List<AnalysisColumn> getColumns(Long id);
   
   List<Map<String, Object>> getSolrResultApplyingQuery(long id, Query query, int facet);

   String getQueryString(Long id);
   
}
