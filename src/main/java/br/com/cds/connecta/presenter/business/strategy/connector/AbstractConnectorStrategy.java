package br.com.cds.connecta.presenter.business.strategy.connector;

import static br.com.cds.connecta.framework.core.util.Util.isNotEmpty;
import static br.com.cds.connecta.framework.core.util.Util.isNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

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
import br.com.cds.connecta.presenter.bean.analysis.DrillColumnValue;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;

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
    
    protected List<ConnectorColumn> toConnectorColumns(Set<AnalysisColumn> analysisColumns) {
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
        
        if (analysisExecuteRequest.getAnalysis() != null 
                && analysisExecuteRequest.getAnalysis().getHasDrill() 
                && analysisExecuteRequest.getDrill() != null) {
            
            String columnToDrill = analysisExecuteRequest.getDrill().getColumnToDrill();
            List<String> columnsToSum = analysisExecuteRequest.getDrill().getColumnsToSum();
            List<DrillColumnValue> listPreviousColumn = 
                    analysisExecuteRequest.getDrill().getListPreviousColumns();
            
            if (Util.isNotNull(columnToDrill)) {
                queryBuilder.addGroupBy(dataContextFactory.getColumn(columnToDrill));
                queryBuilder.addOrder(dataContextFactory.getColumn(columnToDrill));
                
                if(listPreviousColumn != null){
                    for (DrillColumnValue previousColumn : listPreviousColumn) {
                        queryBuilder.addFilter
                        (dataContextFactory.getColumn(previousColumn.getDrillColumn()), 
                                QueryFilterOperator.EQUAL, 
                                new QueryFilterValue(previousColumn.getDrillFilterValue()));
                    }
                }
            }
            
            for (String column : columnsToSum) {
                queryBuilder.addSum(dataContextFactory.getColumn(column));
            }
        }
    }
    
    protected void addPaginationIfDefined(QueryBuilder query, AnalysisExecuteRequest analysisExecuteRequest) {
        if (isNotNull(analysisExecuteRequest.getPagination())
                && isNotNull(analysisExecuteRequest.getPagination().getCount())
                && isNotNull(analysisExecuteRequest.getPagination().getPage())) {
            query.setPagination(analysisExecuteRequest.getPagination().getPage(), analysisExecuteRequest.getPagination().getCount());
        }
    }
    
}
