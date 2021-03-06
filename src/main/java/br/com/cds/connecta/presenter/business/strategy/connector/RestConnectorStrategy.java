package br.com.cds.connecta.presenter.business.strategy.connector;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.context.file.FileDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.file.json.JsonDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.http.Header;
import br.com.cds.connecta.framework.connector2.context.http.HttpDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.http.HttpResponse;
import br.com.cds.connecta.framework.connector2.query.QueryBuilder;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.entity.analysis.RestAnalysis;
import br.com.cds.connecta.presenter.entity.analysis.RestRequestVariableAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.RestDatasourceRequest;
import br.com.cds.connecta.presenter.entity.datasource.RestRequestHeaderDatasource;
import br.com.cds.connecta.presenter.persistence.RestAnalysisRepository;
import br.com.cds.connecta.presenter.persistence.RestDatasourceRequestRepository;
import br.com.cds.connecta.presenter.persistence.specification.RestAnalysisSpecification;
import br.com.cds.connecta.presenter.persistence.specification.RestDatasourceRequestSpecification;

/**
 *
 * @author diego
 */
@Service
@Scope("prototype")
public class RestConnectorStrategy extends AbstractConnectorStrategy {
    
    @Autowired
    private RestAnalysisRepository restAnalysisRepository;

    @Autowired
    private RestDatasourceRequestRepository requestRepository;

    @PersistenceContext
    private EntityManager em;


    @Override
    protected Request makeRequest(AnalysisExecuteRequest analysisExecuteRequest) {
        RestAnalysis restAnalysis = (RestAnalysis) analysisExecuteRequest.getAnalysis();

        RestDatasourceRequest datasourceRequest;
        if (restAnalysis.getRequest() != null) {
            datasourceRequest = requestRepository.findOne(RestDatasourceRequestSpecification.byIdFetch(restAnalysis.getRequest().getId()));
        } else {
            RestAnalysis findOne = restAnalysisRepository.findOne(RestAnalysisSpecification.getRestAnalyis(restAnalysis.getId()));
            datasourceRequest = requestRepository.findOne(RestDatasourceRequestSpecification.byIdFetch(findOne.getRequest().getId()));
            restAnalysis.setRequestVariables(findOne.getRequestVariables());
        }

        datasourceRequest = factoryVariables(datasourceRequest, restAnalysis.getRequestVariables());
        
        List<Header> headers = new ArrayList<>();
        for (RestRequestHeaderDatasource requestHeader : datasourceRequest.getHeaders()) {
            Header header = new Header();
            header.setKey(requestHeader.getKey());
            header.setValue(requestHeader.getValue());
            headers.add(header);
        }
        
        HttpDataContextFactory httpDataContext = new HttpDataContextFactory();
        HttpResponse executeHttp = httpDataContext.executeHttp(
                datasourceRequest.getUrl(),
                datasourceRequest.getVerb(),
                headers,
                datasourceRequest.getBody());

        JsonDataContextFactory jsonDataContextFactory = new JsonDataContextFactory(executeHttp.getBody(), restAnalysis.getTablePath());
        FileDataContextFactory contextFactory = new FileDataContextFactory(jsonDataContextFactory);

        QueryBuilder query = new QueryBuilder();
        query.setColumns(toConnectorColumns(restAnalysis.getAnalysisColumns()));

        addPaginationIfDefined(query, analysisExecuteRequest);
        addDrillIfDefined(query, analysisExecuteRequest, contextFactory);
        addFiltersIfDefined(query, analysisExecuteRequest, contextFactory);

        return new Request(contextFactory, query);

    }
    
    private RestDatasourceRequest factoryVariables(RestDatasourceRequest request, List<RestRequestVariableAnalysis> variables) {
        Gson gson = new Gson();
        String toJson = gson.toJson(request);

        if (variables != null) {
            for (RestRequestVariableAnalysis variable : variables) {
                toJson = toJson.replace(variable.getVariable().getName(), variable.getValue());
            }
        }
        return  gson.fromJson(toJson, RestDatasourceRequest.class);
    }
}
