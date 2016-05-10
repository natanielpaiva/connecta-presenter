package br.com.cds.connecta.presenter.business.applicationService.dataExtractor.impl;

import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.bean.analysisviewer.AnalysisViewerResult;
import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataExtractorAS;
import br.com.cds.connecta.presenter.business.strategy.connector.ConnectorStrategy;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewerColumn;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nataniel Paiva
 */
@Service
@Deprecated
public class DataExtractorAS implements IDataExtractorAS {

    @Autowired
    private IAnalysisDAO analysisDao;

    @Autowired
    private ApplicationContext context;

    @Override
    public AnalysisViewerResult getAnalysisViewerResult(AnalysisViewer analysisViewer) {

        List<Map<String, Object>> data = getDataProvider(analysisViewer);

        AnalysisViewerResult analysViewerResult = new AnalysisViewerResult();
        analysViewerResult.setResult((List<Object>) (Object) data);
        analysViewerResult.setAnalysisViewer(analysisViewer);

        return analysViewerResult;
    }

    @Override
    public List<Map<String, Object>> getDataProvider(AnalysisViewer analysisViewer) {
        List<ConnectorColumn> connectorColumns = getConnectorColumn(analysisViewer.getAnalysisViewerColumns());

        Long id = connectorColumns.get(0).getId();

        Analysis analysis = analysisDao.getByIdColumns(id);
      
        analysis.setAnalysisColumns(getAnalysisColumn(analysisViewer.getAnalysisViewerColumns()));
      
        AnalysisExecuteRequest analysisExecuteRequest = new AnalysisExecuteRequest();
        analysisExecuteRequest.setAnalysis(analysis);
        return executeAnalysis(analysisExecuteRequest);
    }

    @Override
    public List<Map<String, Object>> executeAnalysis(AnalysisExecuteRequest analysisExecuteRequest) {
        ConnectorStrategy strategy = context.getBean(
            analysisExecuteRequest.getAnalysis().getType().getConnectorStrategy()
        );
        
        return strategy.getDataProvider(analysisExecuteRequest);
    }

    @Override
    public List<ConnectorColumn> getConnectorColumn(List<AnalysisViewerColumn> analysisVwColumns) {

        List<ConnectorColumn> connectorColumn = new ArrayList<>();

        for (AnalysisViewerColumn analysisVwColumn : analysisVwColumns) {

            ConnectorColumn column = new ConnectorColumn();
            column.setId(analysisVwColumn.getAnalysisColumn().getId());
            column.setLabel(analysisVwColumn.getAnalysisColumn().getLabel());
            // column.setType(analysisVwColumn.getAnalysisColumn().getType());
            column.setName(analysisVwColumn.getAnalysisColumn().getName());
            column.setFormula(analysisVwColumn.getAnalysisColumn().getFormula());

            connectorColumn.add(column);
        }
        return connectorColumn;
    }
    
    @Override
    public List<AnalysisColumn> getAnalysisColumn(List<AnalysisViewerColumn> analysisVwColumns) {

        List<AnalysisColumn> analysisColumn = new ArrayList<>();

        for (AnalysisViewerColumn analysisVwColumn : analysisVwColumns) {

            AnalysisColumn column = new AnalysisColumn();
            column.setId(analysisVwColumn.getAnalysisColumn().getId());
            column.setLabel(analysisVwColumn.getAnalysisColumn().getLabel());
            // column.setType(analysisVwColumn.getAnalysisColumn().getType());
            column.setName(analysisVwColumn.getAnalysisColumn().getName());
            column.setFormula(analysisVwColumn.getAnalysisColumn().getFormula());

            analysisColumn.add(column);
        }
        return analysisColumn;
    }
}
