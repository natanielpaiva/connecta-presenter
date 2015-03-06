package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.NamedQueries;

/**
 * The persistent class for the TB_ATTRIBUTE database table.
 *
 */
@Entity
@Table(name = "TB_ATTRIBUTE")
@NamedQueries({
    @NamedQuery(name = "Attribute.findAll", query = "SELECT t FROM Attribute t"),
    @NamedQuery(name = "Attribute.findByName", query = "FROM Attribute t WHERE t.name LIKE :name")
})
public class Attribute extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_ATTRIBUTE_SEQ", sequenceName = "TB_ATTRIBUTE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_ATTRIBUTE_SEQ")
    @Column(name = "PK_ATTRIBUTE")
    private Long id;

    @Column(name = "DS_ATTRIBUTE")
    private String description;

    @Column(name = "NM_ATTRIBUTE", unique = true)
    private String name;

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
}
