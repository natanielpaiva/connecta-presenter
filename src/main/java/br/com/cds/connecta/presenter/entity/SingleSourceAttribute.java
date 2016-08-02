package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The persistent class for the TA_ATTR_SNGL_SRC database table.
 *
 */
@Entity
@Table(name = "TA_ATTR_SNGL_SRC")
public class SingleSourceAttribute extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ATTR_SNGL_SRC")
    private Long id;

    @Column(name = "TXT_VALUE", length = 2000)
    private String value;

    @JoinColumn(name = "FK_ATTRIBUTE")
    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.PERSIST, 
        CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Attribute attribute;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

}
