package br.com.cds.connecta.presenter.business.strategy.connector;

import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.context.file.FileDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.file.csv.CSVDataContextFactory;
import br.com.cds.connecta.framework.connector2.query.QueryBuilder;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.entity.analysis.CsvAnalysis;

/**
 *
 * @author diego
 */
@Service
public class CsvConnectorStrategy extends AbstractConnectorStrategy {

    protected Request makeRequest(AnalysisExecuteRequest analysisExecuteRequest) {
        CsvAnalysis csvAnalysis = (CsvAnalysis) analysisExecuteRequest.getAnalysis();

        CSVDataContextFactory csvDataContextFactory = new CSVDataContextFactory(csvAnalysis.getBinaryFile(),
                csvAnalysis.getSeparator().getChar(), csvAnalysis.getQuote().getChar());

        FileDataContextFactory contextFactory;
        contextFactory = new FileDataContextFactory(csvDataContextFactory);

        QueryBuilder queryContext = new QueryBuilder();

        addDrillIfDefined(queryContext, analysisExecuteRequest, contextFactory);
        addFiltersIfDefined(queryContext, analysisExecuteRequest, contextFactory);
        Request request = new Request(contextFactory, queryContext);

        return request;
    }

}
