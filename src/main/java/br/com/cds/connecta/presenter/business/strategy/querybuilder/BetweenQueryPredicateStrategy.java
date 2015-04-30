package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.querybuilder.QueryCondition;
import java.util.List;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class BetweenQueryPredicateStrategy implements QueryPredicateStrategy {

    @Override
    public String getPredicateFor(QueryCondition condition, List<Object> parameters) {
        parameters.add( condition.getValue().getBetween().getStart() );
        parameters.add( condition.getValue().getBetween().getEnd() );
        
        String negation = "";
        if (condition.getPredicate().isNegation()) {
            negation = "NOT";
        }
        
        return  " attr" + condition.getAttribute().getId() + " " + negation + " BETWEEN " + 
               " ? AND ?";
    }
}
