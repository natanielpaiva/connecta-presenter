package br.com.cds.connecta.presenter.business.builder.impl;

import br.com.cds.connecta.presenter.business.builder.IQueryBuilder;
import br.com.cds.connecta.presenter.entity.Query;
import br.com.cds.connecta.presenter.entity.SingleSource;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

/**
 *
 * @author nataniel
 */
@Component
public class SingleSourceQueryBuilder implements IQueryBuilder<SingleSource> {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<SingleSource> fetchResultsFor(Query query) {
        return null;
    }

    @Override
    public Query save(Query query) {
        em.persist(query);
        
        return query;
    }
    
}
