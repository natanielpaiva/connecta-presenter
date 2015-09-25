package br.com.cds.connecta.presenter.business.applicationService.dataExtractor.impl;

import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.framework.connector.util.PrintResult;
import br.com.cds.connecta.presenter.bean.analysisviewer.AnalysisViewerResult;
import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataExtractorAS;
import br.com.cds.connecta.presenter.business.strategy.connector.ConnectorStrategy;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewerColumn;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import java.util.ArrayList;
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

        List<AnalysisViewerColumn> analysisViewerColumns = analysisViewer.getAnalysisViewerColumns();

        List<ConnectorColumn> ConnectorColumn = getAnalysisColumn(analysisViewerColumns);

        Long id = ConnectorColumn.get(0).getId();

        Analysis analysis = analysisDao.getByIdColumns(id);

        ConnectorStrategy strategy = context.getBean(analysis.getDatasource().getType().getConnectorStrategy());
         
        List<Map<String, Object>> dataProvider = strategy.getDataProvider(analysis, ConnectorColumn);
        
        PrintResult printResult = new PrintResult();
        printResult.print(dataProvider);
        
        return dataProvider;
    }

    @Override
    public List<ConnectorColumn> getAnalysisColumn(List<AnalysisViewerColumn> analysisVwColumns) {

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
}
