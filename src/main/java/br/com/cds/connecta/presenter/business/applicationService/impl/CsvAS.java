package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.csv.Csv;
import br.com.cds.connecta.presenter.business.applicationService.ICsvAS;
import br.com.cds.connecta.presenter.entity.analysis.CsvAnalysis;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class CsvAS implements ICsvAS{

    @Override
    public List<Map<String, Object>> getDataCsv(CsvAnalysis csvAnalysis) {
        
        Csv csv = new Csv();
        return csv.getResult(
                csvAnalysis.getBinaryFile(), 
                csvAnalysis.getSeparator().getChar(), 
                csvAnalysis.getQuote().getChar()
        );
        
    }
    
}
