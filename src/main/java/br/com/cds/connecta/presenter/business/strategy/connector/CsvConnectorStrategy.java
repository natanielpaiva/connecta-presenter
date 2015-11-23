package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector.csv.Csv;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.CsvAnalysis;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class CsvConnectorStrategy implements ConnectorStrategy{

    @Override
    public List<Map<String, Object>> getDataProvider(Analysis analysis, List<ConnectorColumn> columns) {
        
        CsvAnalysis csvAnalysis = (CsvAnalysis)analysis;
        
        Csv csv = new Csv();
        return csv.getResult(
                csvAnalysis.getBinaryFile(),
                csvAnalysis.getSeparator().getChar(),
                csvAnalysis.getQuote().getChar(),
                columns
        );
    }
    
}
