package br.com.cds.connecta.presenter.business.builder.impl;

import br.com.cds.connecta.presenter.business.builder.IQueryBuilder;
import br.com.cds.connecta.presenter.business.strategy.querybuilder.QueryPredicateStrategy;
import br.com.cds.connecta.presenter.domain.QueryOperatorEnum;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryCondition;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryGroup;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryStatement;
import br.com.cds.connecta.presenter.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author nataniel
 */
@Component
public class SQLQueryBuilder implements IQueryBuilder<Object> {

    @PersistenceContext
    private EntityManager em;
    
    static Logger logger = Logger.getLogger(SQLQueryBuilder.class);

    @Override
    public List<Object> listResultsFor(Query query, Class<Object> target) {
        javax.persistence.Query q = makeQuery(query, target);
        List resultList = q.getResultList();
        
        return resultList;
    }

    @Override
    public String sqlFor(Query query, Boolean split, Class<Object> target) {
        javax.persistence.Query q = (TypedQuery) makeQuery(query, Object.class);

        String sql = HibernateUtil.toSql(q);

        if (Boolean.TRUE.equals(split)) {
            sql = "WHERE" + sql.split("WHERE")[1];
        }

        return sql;
    }

    private javax.persistence.Query makeQuery(Query query, final Class<Object> target) {
        StringBuilder select = new StringBuilder("SELECT m.PK_SINGLE_SOURCE FROM (SELECT m.PK_SINGLE_SOURCE ");

        String join = " FROM CONNECTA.TB_SINGLE_SOURCE m "
                + " JOIN CONNECTA.TA_ATTR_SNGL_SRC ma "
                + " ON ma.FK_SINGLE_SOURCE = m.PK_SINGLE_SOURCE "
                + " GROUP BY m.PK_SINGLE_SOURCE "
                + " ORDER BY m.PK_SINGLE_SOURCE "
                + ") r "
                + " INNER JOIN CONNECTA.TB_SINGLE_SOURCE m "
                + " ON m.PK_SINGLE_SOURCE = r.PK_SINGLE_SOURCE";

        String where = " WHERE ";
        List<Object> parameters = new ArrayList<>();
        if (query.getStatement() instanceof QueryCondition) {
            select.append(makePivot((QueryCondition) query.getStatement()));
            where += makeConditionPredicate((QueryCondition) query.getStatement(), parameters);
        } else {
            where += makeGroupPredicate((QueryGroup) query.getStatement(), select, parameters);
        }

        javax.persistence.Query nativeQuery = em
                .createNativeQuery(select.append(join).append(where).toString());

        setParameters(nativeQuery, parameters);

        return nativeQuery;
    }

    private String makeConditionPredicate(QueryCondition condition, List<Object> parameters) {
        QueryPredicateStrategy strategy = getStrategyFor(condition);

        return strategy.getPredicateFor(condition, parameters);
    }

    private String makeGroupPredicate(QueryGroup group, StringBuilder select, List<Object> parameters) {
        List<String> predicates = new ArrayList<>();
        for (QueryStatement statement : group.getStatements()) {
            if (statement instanceof QueryCondition) {
                select.append(makePivot((QueryCondition) statement));
                predicates.add(makeConditionPredicate((QueryCondition) statement, parameters));
            } else {
                predicates.add(makeGroupPredicate((QueryGroup) statement, select, parameters));
            }
        }

        String operator = QueryOperatorEnum.AND.equals(group.getOperator())
                ? " AND " : " OR ";

        String conditionGroup = StringUtils.join(predicates, operator);

        return "(" + conditionGroup + ")";
    }

    private QueryPredicateStrategy getStrategyFor(QueryCondition condition) {
        QueryPredicateStrategy strategy = null;

        try {
            strategy = condition.getPredicate().getPredicateStrategyClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

        return strategy;
    }

    private void setParameters(javax.persistence.Query nativeQuery, List<Object> parameters) {
        for (int i = 0; i < parameters.size(); i++) {
            Object get = parameters.get(i);
            nativeQuery.setParameter(i + 1, get);
        }
    }

    private String makePivot(QueryCondition condition) {
        return String.format(
                ", MAX(CASE WHEN ma.FK_ATTRIBUTE = %s THEN ma.TXT_VALUE END) attr%s ",
                condition.getAttribute().getId(),
                condition.getAttribute().getId());
    }

}
