package br.com.cds.connecta.presenter.business.applicationService.dataExtractor.impl;

import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataProvider;
import br.com.cds.connecta.presenter.business.strategy.connector.DataBaseConnectorStrategy;
import br.com.cds.connecta.presenter.business.strategy.connector.RestConnectorStrategy;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewerColumn;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import br.com.cds.connecta.presenter.persistence.impl.DatasourceDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class DataProvider implements IDataProvider {
    
//    @Autowired
//    private IAnalysisDAO analysisDao;
//    
//    @Autowired
//    private DataBaseConnectorStrategy dataBaseStrategy;
//    
//    @Autowired
//    private RestConnectorStrategy restStrategy;
//
//    @Override
//    public List<Map<String, Object>> getDataProvider(AnalysisViewer analysisViewer) {
//        List<Map<String, Object>> dataProvider = null;
//
//        List<AnalysisVwColumn> analysisVwColumn = analysisViewer.getAnalysisVwColumn();
//
//        List<ConnectorColumn> ConnectorColumn = getAnalysisColumn(analysisVwColumn);
//
//        Long id = ConnectorColumn.get(0).getId();
//
//        Analysis analysis = analysisDao.getByIdColumns(id);
//
//        
//        switch(analysis.getDatasource().getType()){
//            case DATABASE:
//                dataProvider = dataBaseStrategy.getDataProvider(analysis, ConnectorColumn);
//                break;
//            case WEBSERVICE:
//                dataProvider = restStrategy.getDataProvider(analysis, ConnectorColumn);
//            break;
//                
//        }
//        return dataProvider;
//    }
//
//    @Override
//    public List<ConnectorColumn> getAnalysisColumn(List<AnalysisVwColumn> analysisVwColumns) {
//
//        List<ConnectorColumn> connectorColumn = new ArrayList<>();
//
//        for (AnalysisVwColumn analysisVwColumn : analysisVwColumns) {
//
//            ConnectorColumn column = new ConnectorColumn();
//            column.setId(analysisVwColumn.getAnalysisColumn().getId());
//            column.setLabel(analysisVwColumn.getAnalysisColumn().getLabel());
//            column.setType(analysisVwColumn.getAnalysisColumn().getType());
//            column.setName(analysisVwColumn.getAnalysisColumn().getName());
//            column.setFormula(analysisVwColumn.getAnalysisColumn().getFormula());
//
//            connectorColumn.add(column);
//        }
//        return connectorColumn;
//    }


}
