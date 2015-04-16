package br.com.cds.connecta.presenter.business.builder.impl;

import br.com.cds.connecta.presenter.business.builder.IQueryBuilder;
import br.com.cds.connecta.presenter.business.strategy.querybuilder.QueryPredicateStrategy;
import br.com.cds.connecta.presenter.domain.QueryOperatorEnum;
import br.com.cds.connecta.presenter.entity.Query;
import br.com.cds.connecta.presenter.entity.QueryCondition;
import br.com.cds.connecta.presenter.entity.QueryGroup;
import br.com.cds.connecta.presenter.entity.QueryStatement;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author nataniel
 */
@Component
public class SingleSourceQueryBuilder implements IQueryBuilder<SingleSource> {

    @PersistenceContext
    private EntityManager em;

    static Logger logger = Logger.getLogger(SingleSourceQueryBuilder.class);

    @Override
    public List<SingleSource> listResultsFor(Query query, final Class<SingleSource> target) {
        TypedQuery typedQuery = makeQuery(query, target);

        return typedQuery.getResultList();
    }

    @Override
    public Query save(Query query) {
        return em.merge(query);
    }

    @Override
    public String sqlFor(Query query, Boolean split, Class<SingleSource> target) {
        TypedQuery typedQuery = makeQuery(query, SingleSource.class);

        String sql = HibernateUtil.toSql(typedQuery);

        if (Boolean.TRUE.equals(split)) {
            sql = "where" + sql.split("where")[1];
        }

        return sql;
    }

    private TypedQuery makeQuery(Query query, final Class<SingleSource> target) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<SingleSource> criteriaQuery = builder.createQuery(target);

        Root<SingleSource> from = criteriaQuery.from(target);

        from.fetch("singleSourceAttributes", JoinType.LEFT)
                .fetch("attribute", JoinType.LEFT);

        criteriaQuery.select(from);

        Join<Object, Object> join = from.join("singleSourceAttributes", JoinType.LEFT);

        Predicate predicate;
        if (query.getStatement() instanceof QueryCondition) {
            predicate = makeConditionPredicate((QueryCondition) query.getStatement(), builder, from, join);
        } else {
            predicate = makeGroupPredicate((QueryGroup) query.getStatement(), builder, from, join);
        }

        criteriaQuery.where(predicate);

        return em.createQuery(criteriaQuery);
    }

    private Predicate makeConditionPredicate(QueryCondition condition, CriteriaBuilder builder, Root<SingleSource> from, Join<Object, Object> join) {
        QueryPredicateStrategy strategy = getStrategyFor(condition);

        return strategy.getPredicateFor(condition, builder, from, join);
    }

    private Predicate makeGroupPredicate(QueryGroup group, CriteriaBuilder builder, Root<SingleSource> from, Join<Object, Object> join) {
        List<Predicate> predicates = new ArrayList<>();
        for (QueryStatement statement : group.getStatements()) {
            if (statement instanceof QueryCondition) {
                predicates.add(makeConditionPredicate((QueryCondition) statement, builder, from, join));
            } else {
                predicates.add(makeGroupPredicate((QueryGroup) statement, builder, from, join));
            }
        }

        // TODO iterate over statements
        Predicate predicate;
        Predicate[] predicateArray = {};
        if (QueryOperatorEnum.AND.equals(group.getOperator())) {
            predicate = builder.and(predicates.toArray(predicateArray));
        } else {
            predicate = builder.or(predicates.toArray(predicateArray));
        }
        return predicate;
    }

    private static QueryPredicateStrategy getStrategyFor(QueryCondition condition) {
        QueryPredicateStrategy strategy = null;

        try {
            strategy = condition.getPredicate().getPredicateStrategyClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

        return strategy;
    }

}
