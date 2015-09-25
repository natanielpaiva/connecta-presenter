package br.com.cds.connecta.presenter.entity.querybuilder;

import br.com.cds.connecta.presenter.domain.QueryPredicateEnum;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "TB_QUERY_CONDITION_SOLR")
public class QueryConditionSolr extends QueryStatement {

    
    @Column(name = "TP_PREDICATE")
    @Enumerated(EnumType.STRING)
    private QueryPredicateEnum predicate;

    @Column(name = "NAME")
    private String name;

    
    @Column(name = "VALUE")
    @Lob
    private Serializable value;

    public QueryPredicateEnum getPredicate() {
        return predicate;
    }

    public void setPredicate(QueryPredicateEnum predicate) {
        this.predicate = predicate;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public QueryConditionValue getValue() {
        return (QueryConditionValue) value;
    }

    public void setValue(QueryConditionValue value) {
        this.value = value;
    }


}
