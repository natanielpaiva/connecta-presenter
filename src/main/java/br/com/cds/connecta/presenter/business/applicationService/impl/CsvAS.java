package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector2.FusionClient;
import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.query.QueryBuilder;
import br.com.cds.connecta.framework.connector2.context.file.FileDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.file.csv.CSVDataContextFactory;
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
public class CsvAS implements ICsvAS {

    @Override
    public List<Map<String, Object>> getDataCsv(CsvAnalysis csvAnalysis) {
        
        CSVDataContextFactory csvDataContext = new CSVDataContextFactory(
                csvAnalysis.getBinaryFile(),
                csvAnalysis.getSeparator().getChar(),
                csvAnalysis.getQuote().getChar()
        );
        FusionClient fusionClient = new FusionClient();

        FileDataContextFactory fileDataContextFactory = new FileDataContextFactory(csvDataContext);

        Request requestCsv = new Request(fileDataContextFactory, new QueryBuilder().setPagination(1, 10));

        return fusionClient.getAll(requestCsv);
 
    }

}
