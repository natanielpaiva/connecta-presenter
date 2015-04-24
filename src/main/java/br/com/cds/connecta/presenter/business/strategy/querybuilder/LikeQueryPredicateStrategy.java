package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.QueryCondition;
import java.util.List;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class LikeQueryPredicateStrategy implements QueryPredicateStrategy {

    @Override
    public String getPredicateFor(QueryCondition condition, List<String> parameters) {
        condition.setValue( "%"+condition.getValue().toUpperCase()+"%" );
        parameters.add(condition.getValue());
        
        String negate = "";
        if (condition.getPredicate().isNegation()) {
            negate = " NOT ";
        }
        
        return " UPPER(attr" + condition.getAttribute().getId() + ") "
                + negate + " LIKE ? ";
    }
}
