package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.querybuilder.QueryCondition;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryConditionSolr;
import java.util.List;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class LikeQueryPredicateStrategy implements QueryPredicateStrategy {

    @Override
    public String getPredicateFor(QueryCondition condition, List<Object> parameters) {
        condition.getValue().setValue( "%"+condition.getValue().getValue()+"%" );
        parameters.add(condition.getValue().getValue());
        
        String negate = "";
        if (condition.getPredicate().isNegation()) {
            negate = " NOT ";
        }
        
        return " attr" + condition.getAttribute().getId() + " "
                + negate + " LIKE ? ";
    }

    @Override
    public String getPredicateForSolr(QueryConditionSolr condition, List<Object> parameters) {
        
        String negate = "+";
        if (condition.getPredicate().isNegation()) {
            negate = "-";
        }
        return "(" + negate + condition.getName() + ":/.*" + condition.getValue().getValue() + ".*/" + ")";
    }
}
