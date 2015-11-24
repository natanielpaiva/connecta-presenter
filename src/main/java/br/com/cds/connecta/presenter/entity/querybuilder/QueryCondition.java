package br.com.cds.connecta.presenter.entity.querybuilder;

import br.com.cds.connecta.presenter.domain.QueryPredicateEnum;
import br.com.cds.connecta.presenter.entity.Attribute;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author nataniel
 */
@Entity
@Table(name = "TB_QUERY_CONDITION")
public class QueryCondition extends QueryStatement {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ATTRIBUTE")
    private Attribute attribute;
    
    @Column(name = "TP_PREDICATE")
    @Enumerated(EnumType.STRING)
    private QueryPredicateEnum predicate;
    
    @Column(name = "VALUE")
    @Lob
    private Serializable value;

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public QueryPredicateEnum getPredicate() {
        return predicate;
    }

    public void setPredicate(QueryPredicateEnum predicate) {
        this.predicate = predicate;
    }

    public QueryConditionValue getValue() {
        return (QueryConditionValue) value;
    }

    public void setValue(QueryConditionValue value) {
        this.value = value;
    }
}
