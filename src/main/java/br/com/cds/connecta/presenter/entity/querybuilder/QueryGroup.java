package br.com.cds.connecta.presenter.entity.querybuilder;

import br.com.cds.connecta.presenter.domain.QueryOperatorEnum;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TB_QUERY_GROUP")
public class QueryGroup extends QueryStatement {

    @Column(name = "TP_OPERATOR")
    @Enumerated(EnumType.STRING)
    @NotNull
    private QueryOperatorEnum operator;
    
//    @Size(min = 2)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_QUERY_STATEMENT")
    private List<QueryStatement> statements;

    public QueryOperatorEnum getOperator() {
        return operator;
    }

    public void setOperator(QueryOperatorEnum operator) {
        this.operator = operator;
    }

    public List<QueryStatement> getStatements() {
        return statements;
    }

    public void setStatements(List<QueryStatement> statements) {
        this.statements = statements;
    }
    
}
