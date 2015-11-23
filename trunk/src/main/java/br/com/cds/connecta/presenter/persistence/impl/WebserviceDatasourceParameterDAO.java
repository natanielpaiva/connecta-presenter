package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasourceParameter;
import java.util.List;
import javax.persistence.Query;

@Repository
public class WebserviceDatasourceParameterDAO extends AbstractBaseJpaDAO<WebserviceDatasourceParameter> {

    public void deleteAll(List<WebserviceDatasourceParameter> parameters) {
        for (WebserviceDatasourceParameter parameter : parameters) {
            delete(parameter);
        }
    }

    public List<WebserviceDatasourceParameter> findAllByWebserviceDatasource(WebserviceDatasource ds) {
        Query q = getEntityManager().createNamedQuery("WebserviceDatasourceParameter.findByDatasource");
        
        q.setParameter("ds", ds);
        
        return q.getResultList();
    }
    
}
