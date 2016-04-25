package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector.rest.Rest;
import br.com.cds.connecta.framework.connector.soap.SoapService;
import br.com.cds.connecta.framework.connector.soap.service.Parameters;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.domain.DatasourceTypeWebservice;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.WebserviceAnalysis;
import br.com.cds.connecta.presenter.entity.analysis.WebserviceAnalysisParameter;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class RestConnectorStrategy implements ConnectorStrategy {

    @Autowired
    private DatasourceRepository dataSourceDao;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Map<String, Object>> getDataProvider(Analysis analysis) {

        List<Map<String, Object>> dataProvider = null;

        WebserviceAnalysis webserviceAnalysis = em.find(WebserviceAnalysis.class, analysis.getId());

        WebserviceDatasource dataSource = (WebserviceDatasource) dataSourceDao.findOne(analysis.getDatasource().getId());

        if (dataSource.getTypeWebservice() == DatasourceTypeWebservice.REST) {
            Rest rest = new Rest();
            dataProvider = rest.getResultTabular(
                    dataSource.getAddress(),
                    toConnectorColumns(webserviceAnalysis.getAnalysisColumns()), webserviceAnalysis.getTablePath());
        } else if (dataSource.getTypeWebservice() == DatasourceTypeWebservice.SOAP) {
            
            List<WebserviceAnalysisParameter> webserviceAnalysisParameter = webserviceAnalysis.getWebserviceAnalysisParameter();
            
            List parameters = new ArrayList<>();
            
            for (WebserviceAnalysisParameter wp : webserviceAnalysisParameter) {
                parameters.add(new Parameters(wp.getParams(), wp.getAttributes(), wp.getValue()));
            }
            
            SoapService soapService = new SoapService(dataSource.getAddress());
            try {
                dataProvider = soapService.factoryResult(
                        webserviceAnalysis.getMethod(),
                        parameters,
                        webserviceAnalysis.getTablePath(),
                        toConnectorColumns(webserviceAnalysis.getAnalysisColumns()));
            } catch (Exception ex) {
                Logger.getLogger(RestConnectorStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return dataProvider;
    }

    private List<ConnectorColumn> toConnectorColumns(List<AnalysisColumn> analysisColumns) {
        List<ConnectorColumn> connectorColumns = new ArrayList<>();
        for (AnalysisColumn analysisColumn : analysisColumns) {
            ConnectorColumn column = new ConnectorColumn();
            column.setId(analysisColumn.getId());
            column.setName(analysisColumn.getName());
            column.setLabel(analysisColumn.getLabel());
            column.setFormula(analysisColumn.getFormula());
//            column.setType("VARCHAR");  FIXME o que fazer?
            connectorColumns.add(column);
}

        return connectorColumns;
    }

//    @Override
//    public List<Map<String, Object>> getDataProvider(Analysis analysis, List<ConnectorColumn> columns) {
//
//        List<Map<String, Object>> dataProvider = null;
//
//        WebserviceAnalysis webserviceAnalysis = em.find(WebserviceAnalysis.class, analysis.getId());
//
//        WebserviceDatasource dataSource = (WebserviceDatasource) dataSourceDao.findOne(analysis.getDatasource().getId());
//
//        if (dataSource.getTypeWebservice() == DatasourceTypeWebservice.REST) {
//            Rest rest = new Rest();
//            dataProvider = rest.getResultTabular(
//                    dataSource.getAddress(),
//                    columns, webserviceAnalysis.getTablePath());
//        } else if (dataSource.getTypeWebservice() == DatasourceTypeWebservice.SOAP) {
//            
//            List<WebserviceAnalysisParameter> webserviceAnalysisParameter = webserviceAnalysis.getWebserviceAnalysisParameter();
//            
//            List parameters = new ArrayList<>();
//            
//            for (WebserviceAnalysisParameter wp : webserviceAnalysisParameter) {
//                parameters.add(new Parameters(wp.getParams(), wp.getAttributes(),wp.getValue()));
//            }
//            
//            SoapService soapService = new SoapService(dataSource.getAddress());
//            try {
//               dataProvider =  soapService.factoryResult(
//                        webserviceAnalysis.getMethod(),
//                        parameters,
//                        webserviceAnalysis.getTablePath(),
//                        columns);
//            } catch (Exception ex) {
//                Logger.getLogger(RestConnectorStrategy.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
//        return dataProvider;
//    }
}
