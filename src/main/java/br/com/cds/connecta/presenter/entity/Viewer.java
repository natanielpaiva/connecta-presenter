package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.framework.amcharts.model.ChartConfiguration;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * The persistent class for the TB_VIEWER database table.
 *
 */
@Entity
@Table(name = "TB_VIEWER")
@NamedQuery(name = "Viewer.findAll", query = "SELECT t FROM Viewer t")
public class Viewer extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_VIEWER_PKVIEWER_GENERATOR", sequenceName = "TB_VIEWER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_VIEWER_PKVIEWER_GENERATOR")
    @Column(name = "PK_VIEWER")
    private Long id;

    @Column(name = "NM_VIEWER")
    private String name;
    
    @Column(name = "DS_VIEWER")
    private String description;
    
    @Lob
    @Column(name="CONFIGURATION")
    private Serializable configuration;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public ChartConfiguration getConfiguration() {
        return (ChartConfiguration) configuration;
    }

    public void setConfiguration(ChartConfiguration configuration) {
        this.configuration = configuration;
    }
}
