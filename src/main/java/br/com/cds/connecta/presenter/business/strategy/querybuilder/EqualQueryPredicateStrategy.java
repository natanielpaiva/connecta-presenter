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
public class EqualQueryPredicateStrategy implements QueryPredicateStrategy {


//    @Override
//    public Criterion makeCriterion(QueryCondition condition, Criteria criteria, String attributePath) {
//        String alias = "alias_" + condition.getId();
//        criteria.createAlias(attributePath, alias);
//
//        Criterion value;
//        if (condition.getPredicate().isNegation()) {
//            value = Restrictions.not(Restrictions.eq(
//                    alias + ".value",
//                    condition.getValue()
//            ));
//        } else {
//            value = Restrictions.eq(
//                    alias + ".value",
//                    condition.getValue()
//            );
//        }
//
//        Conjunction conjunction = Restrictions.conjunction();
//
//        conjunction.add(Restrictions.eq(
//                alias + ".attribute.id",
//                condition.getAttribute().getId()
//        ));
//
//        conjunction.add(value);
//
//        return conjunction;
//    }

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
