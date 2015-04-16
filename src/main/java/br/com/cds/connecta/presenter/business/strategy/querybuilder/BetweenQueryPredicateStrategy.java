package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.QueryCondition;
import br.com.cds.connecta.presenter.entity.SingleSource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class BetweenQueryPredicateStrategy implements QueryPredicateStrategy {

    @Override
    public Predicate getPredicateFor(QueryCondition condition, CriteriaBuilder builder, Root<SingleSource> from, Join<Object, Object> join) {
        
        Path<Object> attribute = join.get("attribute").get("id");
        
        Predicate equal = builder.equal(attribute, condition.getAttribute().getId());

        Expression<String> value = join.get("value");
        Predicate like = builder.equal(
                value, condition.getValue()
        );

        if (condition.getPredicate().isNegation()) {
            like = builder.not(like);
        }

        return builder.and(like, equal);
    }
}
