package br.com.cds.connecta.presenter.business.applicationService.dataExtractor.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataExtractorAS;
import br.com.cds.connecta.presenter.business.strategy.connector.ConnectorStrategy;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.persistence.AnalysisRepository;

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

    @Override
    public List<Map<String, Object>> executeAnalysis(AnalysisExecuteRequest analysisExecuteRequest) throws SQLException {
        ConnectorStrategy strategy = context.getBean(
            analysisExecuteRequest.getAnalysis().getType().getConnectorStrategy()
        );
        
        return strategy.getDataProvider(analysisExecuteRequest);
    }
    
    @Override
    public List<Object> possibleValuesFor(AnalysisExecuteRequest analysisExecuteRequest, Object column) throws SQLException {
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

}
