package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector2.context.http.Header;
import br.com.cds.connecta.framework.connector2.context.http.HttpDataContextFactory;
import br.com.cds.connecta.framework.connector2.context.http.HttpResponse;
import br.com.cds.connecta.presenter.bean.analysis.json.JSONValueParser;
import br.com.cds.connecta.presenter.bean.datasource.RestDatasourceResponse;
import br.com.cds.connecta.presenter.business.applicationService.IRestAS;
import br.com.cds.connecta.presenter.entity.analysis.RestAnalysis;
import br.com.cds.connecta.presenter.entity.analysis.RestRequestVariableAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.RestDatasource;
import br.com.cds.connecta.presenter.entity.datasource.RestDatasourceRequest;
import br.com.cds.connecta.presenter.entity.datasource.RestRequestHeaderDatasource;
import br.com.cds.connecta.presenter.persistence.RestDatasourceRepository;
import br.com.cds.connecta.presenter.persistence.RestDatasourceRequestRepository;
import br.com.cds.connecta.presenter.persistence.specification.RestDatasourceRequestSpecification;
import br.com.cds.connecta.presenter.persistence.specification.RestDatasourceSpecification;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class RestAS implements IRestAS {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RestDatasourceRepository restDatasourceRepository;

    @Autowired
    private RestDatasourceRequestRepository requestRepository;

    @Override
    public RestDatasource getRestDatasource(Long id, String domain) {
        return restDatasourceRepository.findOne(RestDatasourceSpecification.byIdFetch(id));
    }

    public RestDatasourceRequest getRestDatasourceResquest(Long id) {
        return requestRepository.findOne(RestDatasourceRequestSpecification.byIdFetch(id));
    }
    
    @Override
    public RestAnalysis getRestAnalysis(Long id) {

        RestAnalysis request = em.find(RestAnalysis.class, id);
        Hibernate.initialize(request.getRequest().getHeaders());
        Hibernate.initialize(request.getRequestVariables());
       
        return request;
    }
    
    @Override
    public RestDatasourceResponse executeRestAnalysis(RestAnalysis restAnalysis) {

        RestDatasourceRequest request = em.find(RestDatasourceRequest.class, restAnalysis.getRequest().getId());
        List<RestRequestVariableAnalysis> requestVariables = restAnalysis.getRequestVariables();

        RestDatasourceRequest factoryVariables = factoryVariables(request, requestVariables);
        return executeRestRequest(factoryVariables);

    }

    @Override
    public RestDatasourceResponse executeRestRequest(RestDatasourceRequest request) {
        JSONValueParser parser = new JSONValueParser();

        List<Header> headers = new ArrayList<>();
        for (RestRequestHeaderDatasource requestHeader : request.getHeaders()) {
            Header header = new Header();
            header.setKey(requestHeader.getKey());
            header.setValue(requestHeader.getValue());
            headers.add(header);
        }

        HttpDataContextFactory httpDataContextFactory = new HttpDataContextFactory();
        HttpResponse executeHttp = httpDataContextFactory.executeHttp(request.getUrl(), request.getVerb(), headers, request.getBody());

        RestDatasourceResponse restDatasourceResponse = new RestDatasourceResponse(executeHttp);

            restDatasourceResponse.setBodySpecified(parser.parse(executeHttp.getBody()));

        return restDatasourceResponse;
    }

    private RestDatasourceRequest factoryVariables(RestDatasourceRequest request, List<RestRequestVariableAnalysis> variables) {
        Gson gson = new Gson();
        String toJson = gson.toJson(request);

        if (variables != null) {
            for (RestRequestVariableAnalysis variable : variables) {
                toJson = toJson.replace(variable.getVariable().getName(), variable.getValue());
            }
        }
        return gson.fromJson(toJson, RestDatasourceRequest.class);
    }

}
