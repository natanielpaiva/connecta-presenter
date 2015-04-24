package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.QueryCondition;
import java.util.List;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public interface QueryPredicateStrategy {

    String getPredicateFor(QueryCondition condition, List<String> parameters);

}
