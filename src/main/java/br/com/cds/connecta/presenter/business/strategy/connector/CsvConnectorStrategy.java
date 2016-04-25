package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector.csv.Csv;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.CsvAnalysis;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

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
    public List<Map<String, Object>> getDataProvider(Analysis analysis) {
        
        CsvAnalysis csvAnalysis = (CsvAnalysis)analysis;
        
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
    
}
