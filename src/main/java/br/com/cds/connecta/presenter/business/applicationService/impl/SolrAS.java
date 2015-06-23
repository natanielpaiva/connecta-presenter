/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.presenter.business.applicationService.ISolr;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.datasource.SolrDatasource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class SolrAS implements ISolr {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AnalysisColumn> getColumns(Long id) {
        SolrDatasource solr = em.find(SolrDatasource.class, id);
        HttpSolrServer server = new HttpSolrServer(solr.getAddress() + solr.getPath());

        ArrayList<AnalysisColumn> columns = new ArrayList<>();

        LukeRequest lukeRequest = new LukeRequest();
        lukeRequest.setNumTerms(0);
        LukeResponse lukeResponse;
        try {
            lukeResponse = lukeRequest.process(server);
            List<LukeResponse.FieldInfo> sorted = new ArrayList<LukeResponse.FieldInfo>(lukeResponse.getFieldInfo().values());

            for (LukeResponse.FieldInfo infoEntry : sorted) {
                AnalysisColumn column = new AnalysisColumn();
                column.setName(infoEntry.getName());

                columns.add(column);
            }
        } catch (SolrServerException | IOException e) {
        }

        return columns;
    }

}
