package br.com.cds.connecta.presenter.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author nataniel
 */
@Entity
@Table(name = "TB_QUERY")
public class Query implements Serializable {

    @Id
    @SequenceGenerator(name = "TB_QUERY_SEQ", sequenceName = "TB_QUERY_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QUERY_SEQ")
    @Column(name = "PK_QUERY")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<QueryCondition> conditions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<QueryCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<QueryCondition> conditions) {
        this.conditions = conditions;
    }
}
