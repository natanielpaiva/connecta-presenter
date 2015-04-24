package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.QueryCondition;
import java.util.List;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class InQueryPredicateStrategy implements QueryPredicateStrategy {

    @Override
    public String getPredicateFor(QueryCondition condition, List<String> parameters) {
        parameters.add( condition.getValue() );
        
        String operator = "=";
        if (condition.getPredicate().isNegation()) {
            operator = "!=";
        }
        
        return String.format(" attr%s "+operator+" ? ", condition.getAttribute().getId());
    }
}
