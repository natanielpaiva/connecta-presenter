package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.soap.SoapService;
import br.com.cds.connecta.framework.connector.soap.service.Parameters;
import br.com.cds.connecta.presenter.business.applicationService.ISoapAS;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import javax.xml.soap.SOAPException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPathExpressionException;
import org.apache.log4j.Logger;

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
    public List<Map<String, Object>> getResultXmlXpath(Long id, String operation, List<Parameters> parameters, String xPathTable, List<AnalysisColumn> analysisColumn) {

        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);

        String xpathColumns[] = null;

        for (int i = 0; i < analysisColumn.size(); i++) {
            xpathColumns[i] = analysisColumn.get(i).getFormula();
        }

        SoapService soap = new SoapService(webservice.getAddress());
        try {
            soap.factoryResult(operation, parameters, xPathTable, xpathColumns);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return null;

    }

}
