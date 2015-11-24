package br.com.cds.connecta.presenter.business.builder.impl;

import br.com.cds.connecta.presenter.business.builder.IQueryBuilderSorl;
import br.com.cds.connecta.presenter.business.strategy.querybuilder.QueryPredicateStrategy;
import br.com.cds.connecta.presenter.domain.QueryOperatorEnum;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryConditionSolr;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryGroup;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryStatement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diego
 */
@Component
public class SQLQueryBuilderSolr implements IQueryBuilderSorl<Object> {

    static Logger logger = Logger.getLogger(SQLQueryBuilderSolr.class);
    
    @Override
    public String makeQuery(Query query) {
        StringBuilder select = new StringBuilder("");
        String where = "";
        List<Object> parameters = new ArrayList<>();
        if (query.getStatement() instanceof QueryConditionSolr) {
            where += makeConditionPredicate((QueryConditionSolr) query.getStatement(), parameters);
        } else {
            where += makeGroupPredicate((QueryGroup) query.getStatement(), select, parameters);
        }
        return where;
    }
    
    private String makeConditionPredicate(QueryConditionSolr condition, List<Object> parameters) {
        QueryPredicateStrategy strategy = getStrategyFor(condition);

        return strategy.getPredicateForSolr(condition, parameters);
    }

    private String makeGroupPredicate(QueryGroup group, StringBuilder select, List<Object> parameters) {
        List<String> predicates = new ArrayList<>();
        for (QueryStatement statement : group.getStatements()) {
            if (statement instanceof QueryConditionSolr) {
                predicates.add(makeConditionPredicate((QueryConditionSolr) statement, parameters));
            } else {
                predicates.add(makeGroupPredicate((QueryGroup) statement, select, parameters));
            }
        }

        String operator = QueryOperatorEnum.AND.equals(group.getOperator())
                ? " AND " : " OR ";

        String conditionGroup = StringUtils.join(predicates, operator);

        return "(" + conditionGroup + ")";
    }
    
     private QueryPredicateStrategy getStrategyFor(QueryConditionSolr condition) {
        QueryPredicateStrategy strategy = null;

        try {
            strategy = condition.getPredicate().getPredicateStrategyClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

        return strategy;
    }

}
