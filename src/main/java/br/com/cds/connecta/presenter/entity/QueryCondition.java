package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.presenter.domain.QueryPredicateEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
   
}
