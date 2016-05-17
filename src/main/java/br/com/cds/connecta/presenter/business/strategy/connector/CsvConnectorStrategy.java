package br.com.cds.connecta.presenter.business.strategy.connector;

import static br.com.cds.connecta.framework.core.util.Util.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.connector.csv.Csv;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.framework.connector2.FusionClient;
import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.context.file.FileDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.file.csv.CSVDataContextFactory;
import br.com.cds.connecta.framework.connector2.query.QueryBuilder;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisFilter;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.CsvAnalysis;

/**
 *
 * @author diego
 */
@Service
public class CsvConnectorStrategy implements ConnectorStrategy{

//    @Override
//    public List<Map<String, Object>> getDataProvider(Analysis analysis, List<ConnectorColumn> columns) {
//        
//        CsvAnalysis csvAnalysis = (CsvAnalysis)analysis;
//        
//        Csv csv = new Csv();
//        return csv.getResult(
//                csvAnalysis.getBinaryFile(),
//                csvAnalysis.getSeparator().getChar(),
//                csvAnalysis.getQuote().getChar(),
//                columns
//        );
//    }
    
    @Override
    public List<Map<String, Object>> getDataProvider(AnalysisExecuteRequest analysisExecuteRequest) {
        
        CsvAnalysis csvAnalysis = (CsvAnalysis) analysisExecuteRequest.getAnalysis();
        
        Csv csv = new Csv();
        return csv.getResult(
                csvAnalysis.getBinaryFile(),
                csvAnalysis.getSeparator().getChar(),
                csvAnalysis.getQuote().getChar(),
                toConnectorColumns(csvAnalysis.getAnalysisColumns())
        );
    }
    
    private List<ConnectorColumn> toConnectorColumns(List<AnalysisColumn> analysisColumns) {
        List<ConnectorColumn> connectorColumns = new ArrayList<>();
        for(AnalysisColumn analysisColumn : analysisColumns) {
            ConnectorColumn column = new ConnectorColumn();
            column.setId(analysisColumn.getId());
            column.setName(analysisColumn.getName());
            column.setLabel(analysisColumn.getLabel());
            column.setFormula(analysisColumn.getFormula());
//            column.setType("VARCHAR");  FIXME o que fazer?
            connectorColumns.add(column);
}
        
        return connectorColumns;
    }

    
    @Override
    public List<Object> possibleValuesFor(AnalysisExecuteRequest analysisExecuteRequest, String filter) {
        FusionClient fusionClient = new FusionClient();
        Request request = makeRequest(analysisExecuteRequest);
        
        return fusionClient.possibleValuesFor(request, filter);
    }

    private Request makeRequest(AnalysisExecuteRequest analysisExecuteRequest) {
        CsvAnalysis csvAnalysis = (CsvAnalysis) analysisExecuteRequest.getAnalysis();
        
        CSVDataContextFactory csvDataContextFactory = new CSVDataContextFactory(csvAnalysis.getBinaryFile(),
                csvAnalysis.getSeparator().getChar(),csvAnalysis.getQuote().getChar());
          
        FileDataContextFactory contextFactory;      
        contextFactory = new FileDataContextFactory(csvDataContextFactory);
        
        QueryBuilder queryContext = new QueryBuilder();

        addFiltersIfDefined(queryContext, analysisExecuteRequest, contextFactory);
        Request request = new Request(contextFactory, queryContext);
        
        return request;
    }
    
    private void addFiltersIfDefined(QueryBuilder queryBuilder, 
            AnalysisExecuteRequest analysisExecuteRequest, FileDataContextFactory dataContextFactory) {
        if (isNotEmpty(analysisExecuteRequest.getFilters())) {
            for(AnalysisFilter analysisFilter :  analysisExecuteRequest.getFilters()) {
 
                queryBuilder.addFilter(
                    dataContextFactory.getColumn(analysisFilter.getColumnName()),
                    analysisFilter.getOperator(),
                    analysisFilter.getValue()
                );
                
            }
        }
    }
    
}
