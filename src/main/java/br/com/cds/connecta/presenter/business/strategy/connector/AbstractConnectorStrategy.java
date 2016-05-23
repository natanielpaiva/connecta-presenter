package br.com.cds.connecta.presenter.business.strategy.connector;

import static br.com.cds.connecta.framework.core.util.Util.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.zookeeper.server.DatadirCleanupManager;
import org.springframework.context.annotation.FilterType;

import br.com.cds.connecta.framework.connector2.FusionClient;
import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.common.ConnectorColumn;
import br.com.cds.connecta.framework.connector2.common.ContextFactory;
import br.com.cds.connecta.framework.connector2.query.QueryBuilder;
import br.com.cds.connecta.framework.connector2.query.QueryFilterOperator;
import br.com.cds.connecta.framework.connector2.query.QueryFilterValue;
import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisFilter;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;

public abstract class AbstractConnectorStrategy implements ConnectorStrategy {
    
    private final Logger logger = Logger.getLogger(AbstractConnectorStrategy.class);
    
    @Override
    public List<Map<String, Object>> getDataProvider(AnalysisExecuteRequest analysisExecuteRequest) {
        FusionClient fusionClient = new FusionClient();
        Request request = makeRequest(analysisExecuteRequest);
        
        return fusionClient.getAll(request);
    }
    
    @Override
    public List<Object> possibleValuesFor(AnalysisExecuteRequest analysisExecuteRequest, String filter) {
        FusionClient fusionClient = new FusionClient();
        Request request = makeRequest(analysisExecuteRequest);
        
        return fusionClient.possibleValuesFor(request, filter);
    }
    
    /*
     * Abstract Method implemented by subClasses
     */
    protected abstract Request makeRequest(AnalysisExecuteRequest analysisExecuteRequest);
    
    protected List<ConnectorColumn> toConnectorColumns(List<AnalysisColumn> analysisColumns) {
        List<ConnectorColumn> connectorColumns = null;
        
        if (analysisColumns != null && !analysisColumns.isEmpty()) {
            connectorColumns = new ArrayList<>();
            for (AnalysisColumn analysisColumn : analysisColumns) {
                ConnectorColumn column = new ConnectorColumn();
                column.setId(analysisColumn.getId());
                column.setLabel(analysisColumn.getLabel());
                column.setName(analysisColumn.getName());
                column.setFormula(analysisColumn.getFormula());

                logger.info("ADDING COLUMN TO ANALYSIS: " + analysisColumn.getName());

                connectorColumns.add(column);
            }
        }
        
        return connectorColumns;
    }
    
    protected void addFiltersIfDefined(QueryBuilder queryBuilder,
            AnalysisExecuteRequest analysisExecuteRequest, ContextFactory dataContextFactory) {
        if (isNotEmpty(analysisExecuteRequest.getFilters())) {
            for (AnalysisFilter analysisFilter : analysisExecuteRequest.getFilters()) {
                
                queryBuilder.addFilter(
                        dataContextFactory.getColumn(analysisFilter.getColumnName()),
                        analysisFilter.getOperator(),
                        analysisFilter.getValue()
                        );
                
            }
        }
    }
    
    protected void addDrillIfDefined(QueryBuilder queryBuilder, 
            AnalysisExecuteRequest analysisExecuteRequest,
            ContextFactory dataContextFactory) {
        if (analysisExecuteRequest.getAnalysis().getHasDrill() 
                && analysisExecuteRequest.getDrill() != null) {
            
            String columnToDrill = analysisExecuteRequest.getDrill().getColumnToDrill();
            List<String> columnsToSum = analysisExecuteRequest.getDrill().getColumnsToSum();
            
            if (Util.isNotNull(columnToDrill)) {
                queryBuilder.addGroupBy(dataContextFactory.getColumn(columnToDrill));
                String previousDrillColumn = 
                        analysisExecuteRequest.getDrill().getPreviousDrillColumn();
                String filterDrillValue = 
                        analysisExecuteRequest.getDrill().getFilterDrillValue();
                if(previousDrillColumn != null &&
                        filterDrillValue != null){
                    queryBuilder.addFilter
                            (dataContextFactory.getColumn(previousDrillColumn), 
                        QueryFilterOperator.EQUAL, 
                        new QueryFilterValue(filterDrillValue));
                }
            }
            
            for (String column : columnsToSum) {
                queryBuilder.addSum(dataContextFactory.getColumn(column));
            }
        }
    }
    
}
