package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.querybuilder.QueryCondition;
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
}
