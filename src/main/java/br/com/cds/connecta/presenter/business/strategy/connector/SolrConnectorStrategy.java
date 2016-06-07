package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector2.FusionClient;
import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.context.solr.SolrDataContextFactoty;
import br.com.cds.connecta.framework.connector2.query.QueryBuilder;
import static br.com.cds.connecta.framework.core.util.Util.isNotNull;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.business.applicationService.ISolr;
import br.com.cds.connecta.presenter.business.builder.IQueryBuilderSorl;
import br.com.cds.connecta.presenter.domain.SolrRequestTypeEnum;
import br.com.cds.connecta.presenter.entity.analysis.SolrAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.SolrDatasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class SolrConnectorStrategy extends AbstractConnectorStrategy {

    @Autowired
    private DatasourceRepository repository;
     
    @Autowired
    private ISolr solrService;
    
    @Override
    protected Request makeRequest(AnalysisExecuteRequest analysisExecuteRequest) {
         SolrAnalysis solrAnalysis = (SolrAnalysis) analysisExecuteRequest.getAnalysis();
       
         SolrDatasource solrDatasource = (SolrDatasource) repository.findOne(solrAnalysis.getDatasource().getId());
         
         String queryString = null;
         
         if(solrAnalysis.getRequestType() == SolrRequestTypeEnum.TEXT_QUERY){
             queryString = solrAnalysis.getTextQuery();
         }else if(solrAnalysis.getRequestType() == SolrRequestTypeEnum.QUERY_BUILDER){
              queryString = solrService.getQueryString(analysisExecuteRequest.getAnalysis().getId());
         }
        
        SolrDataContextFactoty solrDataContextFactoty = new SolrDataContextFactoty(
                solrDatasource.getAddress() + solrDatasource.getPath(),
                queryString, 
                solrAnalysis.getFacet().intValue());
        QueryBuilder query = new QueryBuilder();
        query.setColumns(toConnectorColumns(solrAnalysis.getAnalysisColumns()));
        
         addPaginationIfDefined(query, analysisExecuteRequest);
        addDrillIfDefined(query, analysisExecuteRequest, solrDataContextFactoty);
        addFiltersIfDefined(query, analysisExecuteRequest, solrDataContextFactoty);
        Request request = new Request(solrDataContextFactoty, query);

        return request;
    }
    
//    @Override
//    private void addPaginationIfDefined(QueryBuilder query, AnalysisExecuteRequest analysisExecuteRequest) {
//        if (isNotNull(analysisExecuteRequest.getPagination()) 
//                && isNotNull(analysisExecuteRequest.getPagination().getCount()) && 
//                isNotNull(analysisExecuteRequest.getPagination().getPage())) {
//            query.setPagination(analysisExecuteRequest.getPagination().getPage(), analysisExecuteRequest.getPagination().getCount());
//        }
//    }
    
//    @Override
//    public List<Object> possibleValuesFor(AnalysisExecuteRequest analysisExecuteRequest, String filter) {
//        FusionClient fusionClient = new FusionClient();
//        Request request = makeRequest(analysisExecuteRequest);
//        
//        return fusionClient.possibleValuesFor(request, filter);
//    }
//    

//     private void addPaginationIfDefined(QueryBuilder query, AnalysisExecuteRequest analysisExecuteRequest) {
//        if (isNotNull(analysisExecuteRequest.getPagination()) 
//                && isNotNull(analysisExecuteRequest.getPagination().getCount()) && 
//                isNotNull(analysisExecuteRequest.getPagination().getPage())) {
//            query.setPagination(analysisExecuteRequest.getPagination().getPage(), analysisExecuteRequest.getPagination().getCount());
//        }
//    }
    
    
//    @Override
//    public List<Map<String, Object>> getDataProvider(AnalysisExecuteRequest analysisExecuteRequest) {
//        List<Map<String, Object>> dataProvider = null;
//
//        SolrDatasource solrDatasource = (SolrDatasource) datasourceRepository.findOne(
//            analysisExecuteRequest.getAnalysis().getDatasource().getId()
//        );
//        
//        String queryString = solrService.getQueryString(analysisExecuteRequest.getAnalysis().getId());
//        
//        Solr sorlConnector = new Solr();
//        try {
//           dataProvider =  sorlConnector.searchSorl(
//                   solrDatasource.getAddress()+solrDatasource.getPath(),
//                   queryString , 10);
//        } catch (SolrServerException | IOException ex) {
//            Logger.getLogger(SolrConnectorStrategy.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return dataProvider;
//    }
    
    
    
    // FIXME passar colunas para o Connector do Solr
//    @Override
//    public List<Map<String, Object>> getDataProvider(AnalysisExecuteRequest analysisExecuteRequest) {
//        List<Map<String, Object>> dataProvider = null;
//
//        SolrDatasource solrDatasource = (SolrDatasource) datasourceRepository.findOne(
//            analysisExecuteRequest.getAnalysis().getDatasource().getId()
//        );
//        
//        String queryString = solrService.getQueryString(analysisExecuteRequest.getAnalysis().getId());
//        
//        Solr sorlConnector = new Solr();
//        try {
//           dataProvider =  sorlConnector.searchSorl(
//                   solrDatasource.getAddress()+solrDatasource.getPath(),
//                   queryString , 10);
//        } catch (SolrServerException | IOException ex) {
//            Logger.getLogger(SolrConnectorStrategy.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return dataProvider;
//    }

//    @Override
//    public List<Map<String, Object>> getDataProvider(Analysis analysis, List<ConnectorColumn> columns) {
//
//        SolrDatasource solrDatasource = (SolrDatasource)datasourceRepository.findOne(analysis.getDatasource().getId());
//        
//        String queryString = solrService.getQueryString(analysis.getId());
//        
//        Solr sorlConnector = new Solr();
//        try {
//           dataProvider =  sorlConnector.searchSorl(
//                   solrDatasource.getAddress()+solrDatasource.getPath(),
//                   queryString , 10);
//        } catch (SolrServerException ex) {
//            Logger.getLogger(SolrConnectorStrategy.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(SolrConnectorStrategy.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dataProvider;
//    }

   

}
