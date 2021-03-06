package br.com.cds.connecta.presenter.entity.querybuilder;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author nataniel
 */
@Entity
@Table(name = "TB_QUERY")
public class Query implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_QUERY")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="FK_QUERY_STATEMENT")
    private QueryStatement statement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QueryStatement getStatement() {
        return statement;
    }

    public void setStatement(QueryStatement statement) {
        this.statement = statement;
    }
    
}
