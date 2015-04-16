package br.com.cds.connecta.presenter.domain;

import br.com.cds.connecta.presenter.business.strategy.querybuilder.EqualQueryPredicateStrategy;
import br.com.cds.connecta.presenter.business.strategy.querybuilder.LikeQueryPredicateStrategy;
import br.com.cds.connecta.presenter.business.strategy.querybuilder.QueryPredicateStrategy;

/**
 *
 * @author nataniel
 */
public enum QueryPredicateEnum {

    EQUAL(EqualQueryPredicateStrategy.class, false),
    NOT_EQUAL(EqualQueryPredicateStrategy.class, true),
    LIKE(LikeQueryPredicateStrategy.class, false),
    NOT_LIKE(LikeQueryPredicateStrategy.class, true),
    IN(null, false),
    NOT_IN(null, true),
    BETWEEN(null, false),
    NOT_BETWEEN(null, true);

    private final Class<? extends QueryPredicateStrategy> strategy;
    private final boolean negation;

    private QueryPredicateEnum(Class<? extends QueryPredicateStrategy> strategy, boolean negation) {
        this.strategy = strategy;
        this.negation = negation;
    }

    public Class<? extends QueryPredicateStrategy> getPredicateStrategyClass() {
        return strategy;
    }

    public boolean isNegation() {
        return negation;
    }

}
