package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;

@Entity
@Table(name = "TA_ATTR_ANALYSIS")
@NamedQuery(name = "AnalysisAttribute.findAll", query = "SELECT t FROM AnalysisAttribute t")
public class AnalysisAttribute extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TA_ATTR_ANALYSIS_PKATTRIBUTE_GENERATOR", sequenceName = "TA_ATTR_ANALYSIS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_ATTR_ANALYSIS_PKATTRIBUTE_GENERATOR")
    @Column(name = "PK_ATTR_ANALYSIS")
    private Long id;

    @Column(name = "TXT_VALUE")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ANALYSIS")
    private Analysis analysis;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ATTRIBUTE")
    private Attribute attribute;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
    
}
