package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.framework.connector.soap.service.Parameters;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import java.util.List;
import java.util.Map;
import javax.xml.transform.dom.DOMSource;

/**
 *
 * @author diego
 */
public interface ISoapAS {
    
    List<AnalysisColumn> getMethodsSoap(Long id);
    
    DOMSource getDOMSourceSoap(Long id, String operation, List<Parameters> parameters);
    
    List<Map<String, Object>> getResultXmlXpath(Long id, String operation, List<Parameters> parameters, String xPathTable, List<AnalysisColumn> analysisColumn); 
}
