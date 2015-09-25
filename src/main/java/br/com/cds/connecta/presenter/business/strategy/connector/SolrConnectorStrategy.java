package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector.solr.Solr;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.business.applicationService.ISolr;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.datasource.SolrDatasource;
import br.com.cds.connecta.presenter.persistence.impl.DatasourceDAO;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class SolrConnectorStrategy implements ConnectorStrategy {

    @Autowired
    private DatasourceDAO dataSourceDao;

    @Autowired
    private ISolr solrService;
    
    List<Map<String, Object>> dataProvider;

    @Override
    public List<Map<String, Object>> getDataProvider(Analysis analysis, List<ConnectorColumn> columns) {

        SolrDatasource solrDatasource = (SolrDatasource)dataSourceDao.findOne(analysis.getDatasource().getId());
        
        String queryString = solrService.getQueryString(analysis.getId());
        
        Solr sorlConnector = new Solr();
        try {
           dataProvider =  sorlConnector.searchSorl(
                   solrDatasource.getAddress()+solrDatasource.getPath(),
                   queryString , 10);
        } catch (SolrServerException ex) {
            Logger.getLogger(SolrConnectorStrategy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SolrConnectorStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataProvider;
    }

}
