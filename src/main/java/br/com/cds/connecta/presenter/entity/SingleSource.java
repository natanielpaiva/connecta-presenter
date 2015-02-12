package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.presenter.domain.SingleSourceTypeEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * The persistent class for the TB_SINGLE_SOURCE database table.
 *
 */
@Entity
@Table(name = "TB_SINGLE_SOURCE")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "SingleSource.findAll", query = "SELECT s FROM SingleSource s")
public class SingleSource extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_SINGLE_SOURCE_SEQ", sequenceName = "TB_SINGLE_SOURCE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_SINGLE_SOURCE_SEQ")
    @Column(name = "PK_SINGLE_SOURCE")
    private Long id;

    @Column(name = "DS_SINGLE_SOURCE")
    private String description;

    @Column(name = "NM_SINGLE_SOURCE")
    private String name;

    @Column(name = "TP_SINGLE_SOURCE")
    @Enumerated(EnumType.STRING)
    private SingleSourceTypeEnum type;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SingleSourceTypeEnum getType() {
        return type;
    }

    public void setType(SingleSourceTypeEnum type) {
        this.type = type;
    }
}