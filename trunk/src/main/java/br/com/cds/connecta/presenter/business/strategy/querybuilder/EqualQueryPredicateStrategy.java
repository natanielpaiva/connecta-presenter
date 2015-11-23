package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.querybuilder.QueryCondition;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryConditionSolr;
import java.util.List;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class EqualQueryPredicateStrategy implements QueryPredicateStrategy {

    @Override
    public String getPredicateFor(QueryCondition condition, List<Object> parameters) {
        parameters.add( condition.getValue().getValue() );
        
        String operator = "=";
        if (condition.getPredicate().isNegation()) {
            operator = "!=";
        }
        
        return String.format(" attr%s "+operator+" ? ", condition.getAttribute().getId());
    }

    @Override
    public String getPredicateForSolr(QueryConditionSolr condition, List<Object> parameters) {
        
        String operator = "+";
        if (condition.getPredicate().isNegation()) {
            operator = "-";
        }
        
        return "(" +  operator + condition.getName() +  ":" + condition.getValue().getValue() + ")";
    }

}
