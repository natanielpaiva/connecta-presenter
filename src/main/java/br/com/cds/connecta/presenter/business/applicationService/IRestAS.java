package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.analysis.WebserviceAnalysis;

/**
 *
 * @author diego
 */
public interface IRestAS {

    public Object getJsonRest(Long id);

    public Object getResultApplyingJsonPath(Long id, WebserviceAnalysis ws);

    public Object getJsonPartJsonPath(Long id, WebserviceAnalysis ws);

}
