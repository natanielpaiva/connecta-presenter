package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.soap.SOAPException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.connector.soap.SoapService;
import br.com.cds.connecta.framework.connector.soap.service.Parameters;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.business.applicationService.ISoapAS;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.WebserviceAnalysis;
import br.com.cds.connecta.presenter.entity.analysis.WebserviceAnalysisParameter;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;

@Service
public class SoapAS implements ISoapAS {

    @PersistenceContext
    private EntityManager em;

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public List getMethodsSoap(Long id) {

        List operation = null;
        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);
        SoapService soap = new SoapService(webservice.getAddress());

        try {
            operation = soap.getOperation();
        } catch (XPathExpressionException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return operation;
    }

    @Override
    public DOMSource getDOMSourceSoap(Long id, String operation, List<Parameters> parameters) {

        DOMSource response = null;
        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);
        SoapService soap = new SoapService(webservice.getAddress());

        try {
            response = soap.getDomSource(operation, parameters);
        } catch (SOAPException | IOException | XPathExpressionException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return response;
    }

    @Override
    public List<Map<String, Object>> getResultXmlXpath(Long id, WebserviceAnalysis ws) {
        List<Map<String, Object>> factoryResult = null;
        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);

        Set<AnalysisColumn> analysisColumns = ws.getAnalysisColumns();

        List<ConnectorColumn> connectorColumn = new ArrayList<>();

        for(AnalysisColumn column : analysisColumns){
        	ConnectorColumn connectorColumn1 = new ConnectorColumn();

            connectorColumn1.setId(column.getId());
            connectorColumn1.setName(column.getName());
            connectorColumn1.setLabel(column.getLabel());
            connectorColumn1.setFormula(column.getFormula());
            //connectorColumn1.setType(analysisColumns.get(i).getType());
            
            connectorColumn.add(connectorColumn1);
        }
        
        List parameters = new ArrayList<>();
        List<WebserviceAnalysisParameter> webParameter = ws.getWebserviceAnalysisParameter();

        for (WebserviceAnalysisParameter wap : webParameter) {
            parameters.add(new Parameters(wap.getParams(), wap.getAttributes(), wap.getValue()));
        }

        SoapService soap = new SoapService(webservice.getAddress());
        try {
            factoryResult = soap.factoryResult(ws.getMethod(), parameters, ws.getTablePath(), connectorColumn);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return factoryResult;
    }
}
