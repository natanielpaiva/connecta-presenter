package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.QueryCondition;
import br.com.cds.connecta.presenter.entity.SingleSource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public interface QueryPredicateStrategy {

    Predicate getPredicateFor(QueryCondition condition, CriteriaBuilder builder, Root<SingleSource> from, Join<Object, Object> join);
}
