package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.bean.datasource.RestDatasourceResponse;
import br.com.cds.connecta.presenter.entity.analysis.RestAnalysis;
import br.com.cds.connecta.presenter.entity.datasource.RestDatasource;
import br.com.cds.connecta.presenter.entity.datasource.RestDatasourceRequest;

/**
 *
 * @author diego
 */
public interface IRestAS {

    RestAnalysis getRestAnalysis(Long id);
    
    RestDatasource getRestDatasource(Long id, String domain);
    
    RestDatasourceResponse executeRestRequest(RestDatasourceRequest datasourceRequest);
    
    RestDatasourceResponse executeRestAnalysis(RestAnalysis restAnalysis);
    
}
