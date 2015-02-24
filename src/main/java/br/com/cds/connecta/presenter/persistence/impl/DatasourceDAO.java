package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import java.util.List;
import javax.persistence.Query;

@Repository
public class DatasourceDAO extends AbstractBaseJpaDAO<Datasource> {

    public List<Datasource> list() {
        Query query = getEntityManager().createNamedQuery("Datasource.findAll");
        
        List datasources = query.getResultList();
        
        return datasources;
    }
}
