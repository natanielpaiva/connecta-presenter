package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.connector.rest.Rest;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.business.applicationService.IRestAS;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.WebserviceAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;

/**
 *
 * @author diego
 */
@Service
public class RestAS implements IRestAS {

    @PersistenceContext
    private EntityManager em;

    private final Logger logger = Logger.getLogger(getClass());

    /**
     * Retorna um json de acordo com o Id do webservice informado.
     *
     * @param id
     * @return
     */
    @Override
    public Object getJsonRest(Long id) {
        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);
        Rest rest = new Rest();
        Object json = rest.getJson(webservice.getAddress());
        return json;
    }

    @Override
    public Object getResultApplyingJsonPath(Long id, WebserviceAnalysis ws) {

        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);

        Set<AnalysisColumn> analysisColumns = ws.getAnalysisColumns();

        ArrayList<ConnectorColumn> columns = new ArrayList<>();
        
        for(AnalysisColumn column : analysisColumns){
        	ConnectorColumn connectorColumn = new ConnectorColumn();
        	connectorColumn.setId(column.getId());
            connectorColumn.setFormula(column.getFormula());
            connectorColumn.setLabel(column.getLabel());
            connectorColumn.setName(column.getName());
            
            columns.add(connectorColumn);
        }
        
        Rest rest = new Rest();
        List<Map<String, Object>> factoryResultMetaModel;
        
        factoryResultMetaModel = rest.getResultTabular(webservice.getAddress(), columns, ws.getTablePath());

        return factoryResultMetaModel;
    }
    
    @Override
    public Object getJsonPartJsonPath(Long id, WebserviceAnalysis ws) {

        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);

        Rest rest = new Rest();
        
        Object extractPartJson = rest.extractPartJson(webservice.getAddress(), ws.getTablePath());
        
        return extractPartJson;
    }

}
