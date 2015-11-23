package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.analysis.CsvAnalysis;
import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public interface ICsvAS {
    
    List<Map<String, Object>>  getDataCsv(CsvAnalysis csvAnalysis);
}
