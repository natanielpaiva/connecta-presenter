package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.NamedQueries;

/**
 * The persistent class for the TB_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_ANALYSIS")
@NamedQueries({
    @NamedQuery(name = "Analysis.findAll", query = "SELECT t FROM Analysis t")
})
public class Analysis extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_ANALYSIS_SEQ", sequenceName = "TB_ANALYSIS_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_ANALYSIS_SEQ")
    @Column(name = "PK_ANALYSIS")
    private Long id;

    @Column(name = "DS_ANALYSIS")
    private String description;

    @Column(name = "NM_ANALYSIS")
    private String name;

    @Column(name = "TP_ANALYSIS")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_DATASOURCE")
    private Datasource datasource;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    
}
