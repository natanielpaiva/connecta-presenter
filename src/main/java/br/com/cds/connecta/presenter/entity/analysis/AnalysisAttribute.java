package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.entity.Attribute;

@Entity
@Table(name = "TA_ATTR_ANALYSIS")
public class AnalysisAttribute extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ATTR_ANALYSIS")
    private Long id;

    @Column(name = "TXT_VALUE")
    private String value;

    @JoinColumn(name = "FK_ATTRIBUTE")
    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.PERSIST, 
        CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
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

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

}
