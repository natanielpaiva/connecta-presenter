package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.presenter.business.applicationService.IQueryAS;
import br.com.cds.connecta.presenter.business.builder.IQueryBuilder;
import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryGroup;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryStatement;
import br.com.cds.connecta.presenter.persistence.ISingleSourceDAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nataniel
 */
@Service
public class QueryAS implements IQueryAS {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IQueryBuilder builder;

    @Autowired
    private ISingleSourceDAO singleSourceDAO;


    @Override
    public Query save(Query query) {
        return em.merge(query);
    }

    @Override
    public Query saveGetById(Long id) {
        Query query = em.find(Query.class, id);

        initQueryStatements(query.getStatement());

        return query;
    }

    private void initQueryStatements(QueryStatement statement) {
        Hibernate.initialize(statement);
        if (statement instanceof QueryGroup) {
            Hibernate.initialize(((QueryGroup) statement).getStatements());
            for (QueryStatement qs : ((QueryGroup) statement).getStatements()) {
                initQueryStatements(qs);
            }
        }
    }

    @Override
    public List<FileSingleSource> getSingleSourceByIds(Query query, Class<Object> target) {
        List listResultsFor = builder.listResultsFor(query, target);

        List<Long> list = new ArrayList<>();
        for (Iterator it = listResultsFor.iterator(); it.hasNext();) {
            Number o = (Number) it.next();
            list.add(o.longValue());
        }

        List<FileSingleSource> result = new ArrayList<>();
        if (!list.isEmpty()) {
            result = singleSourceDAO.getByIds(list);
        } 
        return result;
    }

}
