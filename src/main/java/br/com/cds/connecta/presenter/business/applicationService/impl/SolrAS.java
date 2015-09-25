package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.solr.Solr;
import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.business.applicationService.ISolr;
import br.com.cds.connecta.presenter.business.builder.IQueryBuilderSorl;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.SolrAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.SolrDatasource;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class SolrAS implements ISolr {

    @Autowired
    private IQueryBuilderSorl builder;

    @PersistenceContext
    private EntityManager em;
    
    private Solr solr = new Solr();

    private static final Logger logger = Logger.getLogger(SoapAS.class.getName());

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<AnalysisColumn> getColumns(Long id) {
        ArrayList<AnalysisColumn> analysisColumnsList = new ArrayList<>();

        SolrDatasource solrDatasource = em.find(SolrDatasource.class, id);

        List<ConnectorColumn> connectorColumns = solr.getColumns(solrDatasource.getAddress() + solrDatasource.getPath());

        for (ConnectorColumn cc : connectorColumns) {
            AnalysisColumn analysisColumn = new AnalysisColumn();
            analysisColumn.setFormula(cc.getFormula());
            analysisColumn.setName(cc.getName());
            analysisColumn.setLabel(cc.getLabel());

            analysisColumnsList.add(analysisColumn);
        }

        return analysisColumnsList;

    }

    @Override
    public List<Map<String, Object>> getSolrResultApplyingQuery(long id, Query query, int facet) {
        SolrDatasource solrDatasource = em.find(SolrDatasource.class, id);

        String stringQuerySolr = builder.makeQuery(query);

        logger.log(Level.INFO, "Solr query: {0}", stringQuerySolr);

        List<Map<String, Object>> searchSorl = null;
        try {
            searchSorl = solr.searchSorl(solrDatasource.getAddress() + solrDatasource.getPath(), stringQuerySolr,  facet);
        } catch (SolrServerException | IOException ex) {
            Logger.getLogger(SolrAS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return searchSorl;
    }

    @Override
    public String getQueryString(Long id) {

        SolrAnalysis solrAnalysis = em.find(SolrAnalysis.class, id);

        Query query = em.find(Query.class, solrAnalysis.getQuery().getId());

        String stringQuerySolr = builder.makeQuery(query);

        return stringQuerySolr;
    }

}

