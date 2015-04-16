package br.com.cds.connecta.presenter.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
@Entity
@Table(name = "TB_QUERY_STATEMENT")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @Type(value = QueryGroup.class, name = "GROUP"),
    @Type(value = QueryCondition.class, name = "CONDITION")
})
public class QueryStatement implements Serializable {
    
    @Id
    @GeneratedValue(generator = "TB_QUERY_STATEMENT_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TB_QUERY_STATEMENT_SEQ", sequenceName = "TB_QUERY_STATEMENT_SEQ",
            initialValue = 1, allocationSize = 1)
    @Column(name = "PK_QUERY_STATEMENT")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
