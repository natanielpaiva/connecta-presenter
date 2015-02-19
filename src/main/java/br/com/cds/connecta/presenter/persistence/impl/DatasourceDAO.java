package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class DatasourceDAO extends AbstractBaseJpaDAO<Datasource> {
    
    @PersistenceContext
    private EntityManager em;

    public List<Datasource> list() {
        Query query = em.createNamedQuery("Datasource.findAll");
        
        List datasources = query.getResultList();
        
        return datasources;
    }

    

}
