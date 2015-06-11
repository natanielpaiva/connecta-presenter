package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.soap.SoapService;
import br.com.cds.connecta.framework.connector.soap.service.Parameters;
import br.com.cds.connecta.presenter.business.applicationService.ISoapAS;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import java.io.IOException;
import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.log4j.Logger;

@Service
public class SoapAS implements ISoapAS {

    @PersistenceContext
    private EntityManager em;
    private final Logger logger = Logger.getLogger(SoapAS.class);

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

    /**
     *
     * @param id
     * @param operation
     * @param parameters
     * @return
     */
    @Override
    public String getColumnsSoap(Long id, String operation, List<Parameters> parameters) {
        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);

        logger.debug("---------------");
        logger.debug("---------------" + webservice.getAddress());
        logger.debug("---------------" + operation);

        SoapService soap = new SoapService(webservice.getAddress());
//          SoapService soap = new SoapService("http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx?WSDL");
//          List parameters2 = new ArrayList<Parameters>();
//        
//        String operation2 = "CalcPrazo";
//        parameters2.add(new Parameters("nCdServico", "41106"));
//        parameters2.add(new Parameters("sCepOrigem", "64002150"));
//        parameters2.add(new Parameters("sCepDestino", "72594235"));

        String responseXml = null;
        try {
            responseXml = soap.ResponseXml(operation, parameters);
        } catch (SOAPException | IOException | XPathExpressionException ex) {
            logger.error(ex.getMessage(), ex);
        }

        logger.debug("-->>>>>>>>>>>>>>>>>>>>>>>>>-" + responseXml);

        return responseXml;
    }

}
