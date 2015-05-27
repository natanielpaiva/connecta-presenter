
package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.soap.SoapService;
import br.com.cds.connecta.framework.connector.soap.service.Parameters;
import br.com.cds.connecta.presenter.business.applicationService.ISoapAS;
import br.com.cds.connecta.presenter.entity.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

@Service
public class SoapAS implements ISoapAS {
   
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List getMethodsSoap(Long id) {
        List operation = null;
        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);
        
         SoapService soap = new SoapService(webservice.getAddress());
       
        try {
             operation = soap.getOperation();
        } catch (XPathExpressionException ex) {
            Logger.getLogger(SoapAS.class.getName()).log(Level.SEVERE, null, ex);
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
        String ResponseXml = null;
        WebserviceDatasource webservice = em.find(WebserviceDatasource.class, id);
        
        System.out.println("---------------");
        System.out.println("---------------" + webservice.getAddress());
        //System.out.println("---------------" + uri);
        System.out.println("---------------" + operation);
        
         SoapService soap = new SoapService(webservice.getAddress());
//          SoapService soap = new SoapService("http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx?WSDL");
//          List parameters2 = new ArrayList<Parameters>();
//        
//        String operation2 = "CalcPrazo";
//        parameters2.add(new Parameters("nCdServico", "41106"));
//        parameters2.add(new Parameters("sCepOrigem", "64002150"));
//        parameters2.add(new Parameters("sCepDestino", "72594235"));
         
        try {
            ResponseXml = soap.ResponseXml(operation, parameters);

        } catch (SOAPException ex) {
            Logger.getLogger(SoapAS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoapAS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(SoapAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("-->>>>>>>>>>>>>>>>>>>>>>>>>-" + ResponseXml);
        return ResponseXml;
    }

}
