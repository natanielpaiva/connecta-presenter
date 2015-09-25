package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.querybuilder.QueryCondition;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryConditionSolr;
import java.util.List;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public interface QueryPredicateStrategy {

    String getPredicateFor(QueryCondition condition, List<Object> parameters);
    
    String getPredicateForSolr(QueryConditionSolr condition, List<Object> parameters);
}
