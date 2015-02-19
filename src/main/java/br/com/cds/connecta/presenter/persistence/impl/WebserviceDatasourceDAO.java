package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasourceParameter;

@Repository
public class WebserviceDatasourceDAO extends AbstractBaseJpaDAO<WebserviceDatasource> {

    @Override
    public WebserviceDatasource saveOrUpdate(WebserviceDatasource entity) {
        WebserviceDatasource ds = super.saveOrUpdate(entity);
        
        for (WebserviceDatasourceParameter parameter : entity.getParameters()) {
            parameter.setWebservice(ds);
            getEntityManager().persist(parameter);
        }
        
        return ds;
    }
    
}
