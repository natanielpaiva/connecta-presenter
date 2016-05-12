package br.com.cds.connecta.presenter.business.applicationService.dataExtractor.impl;

import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataExtractorAS;
import br.com.cds.connecta.presenter.business.strategy.connector.ConnectorStrategy;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.persistence.AnalysisRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class DataExtractorAS implements IDataExtractorAS {

    @Autowired
    private AnalysisRepository analysisRepository;

    @Autowired
    private ApplicationContext context;

//    @Override
//    public AnalysisViewerResult getAnalysisViewerResult(AnalysisViewer analysisViewer) {
//
//        List<Map<String, Object>> data = getDataProvider(analysisViewer);
//
//        AnalysisViewerResult analysViewerResult = new AnalysisViewerResult();
//        analysViewerResult.setResult((List<Object>) (Object) data);
//        analysViewerResult.setAnalysisViewer(analysisViewer);
//
//        return analysViewerResult;
//    }
//
//    @Override
//    public List<Map<String, Object>> getDataProvider(AnalysisViewer analysisViewer) {
//        List<ConnectorColumn> connectorColumns = getConnectorColumn(analysisViewer.getAnalysisViewerColumns());
//
//        Long id = connectorColumns.get(0).getId();
//
//        Analysis analysis = analysisDao.getByIdColumns(id);
//      
//        analysis.setAnalysisColumns(getAnalysisColumn(analysisViewer.getAnalysisViewerColumns()));
//      
//        AnalysisExecuteRequest analysisExecuteRequest = new AnalysisExecuteRequest();
//        analysisExecuteRequest.setAnalysis(analysis);
//        
//        return executeAnalysis(analysisExecuteRequest);
//    }

    @Override
    public List<Map<String, Object>> executeAnalysis(AnalysisExecuteRequest analysisExecuteRequest) {
        ConnectorStrategy strategy = context.getBean(
            analysisExecuteRequest.getAnalysis().getType().getConnectorStrategy()
        );
        
        return strategy.getDataProvider(analysisExecuteRequest);
    }
    
    @Override
    public List<Object> possibleValuesFor(AnalysisExecuteRequest analysisExecuteRequest, Object column) {
        ConnectorStrategy strategy = context.getBean(
            analysisExecuteRequest.getAnalysis().getType().getConnectorStrategy()
        );
        
        if (column instanceof Long) {
            AnalysisColumn filter = analysisRepository.findColumnById((Long) column);
            column = filter.getName();
        }
        
        List<Object> possibleValues = strategy.possibleValuesFor(analysisExecuteRequest, (String) column);
        
        return possibleValues;
    }

////    @Override
//    private List<ConnectorColumn> getConnectorColumn(List<AnalysisViewerColumn> analysisViewerColumns) {
//        List<ConnectorColumn> connectorColumn = new ArrayList<>();
//
//        for (AnalysisViewerColumn analysisViewerColumn : analysisViewerColumns) {
//            ConnectorColumn column = new ConnectorColumn();
//            column.setId(analysisViewerColumn.getAnalysisColumn().getId());
//            column.setLabel(analysisViewerColumn.getAnalysisColumn().getLabel());
//            // column.setType(analysisVwColumn.getAnalysisColumn().getType());
//            column.setName(analysisViewerColumn.getAnalysisColumn().getName());
//            column.setFormula(analysisViewerColumn.getAnalysisColumn().getFormula());
//
//            connectorColumn.add(column);
//        }
//        
//        return connectorColumn;
//    }
//    
////    @Override
//    private List<AnalysisColumn> getAnalysisColumn(List<AnalysisViewerColumn> analysisViewerColumns) {
//
//        List<AnalysisColumn> analysisColumn = new ArrayList<>();
//
//        for (AnalysisViewerColumn analysisViewerColumn : analysisViewerColumns) {
//
//            AnalysisColumn column = new AnalysisColumn();
//            column.setId(analysisViewerColumn.getAnalysisColumn().getId());
//            column.setLabel(analysisViewerColumn.getAnalysisColumn().getLabel());
//            // column.setType(analysisVwColumn.getAnalysisColumn().getType());
//            column.setName(analysisViewerColumn.getAnalysisColumn().getName());
//            column.setFormula(analysisViewerColumn.getAnalysisColumn().getFormula());
//
//            analysisColumn.add(column);
//        }
//        
//        return analysisColumn;
//    }
    
}
