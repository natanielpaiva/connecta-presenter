package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.presenter.domain.QueryOperatorEnum;
import br.com.cds.connecta.presenter.domain.QueryPredicateEnum;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author nataniel
 */
@Entity
@Table(name = "TB_QUERY_CONDITION")
public class QueryCondition implements Serializable {
    
    @Id
    @SequenceGenerator(name = "TB_QUERY_CONDITION_SEQ", sequenceName = "TB_QUERY_CONDITION_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QUERY_CONDITION_SEQ")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ATTRIBUTE")
    private Attribute attribute;
    
    @Column(name = "TP_PREDICATE")
    @Enumerated(EnumType.STRING)
    private QueryPredicateEnum predicate;
    
    @Column(name = "VALUE")
    private String value;
    
    @Column(name = "TP_OPERATOR")
    @Enumerated(EnumType.STRING)
    private QueryOperatorEnum operator;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_QUERY_CONDITION")
    private List<QueryCondition> conditions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public QueryOperatorEnum getOperator() {
        return operator;
    }

    public void setOperator(QueryOperatorEnum operator) {
        this.operator = operator;
    }

    public List<QueryCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<QueryCondition> conditions) {
        this.conditions = conditions;
    }
    
}
