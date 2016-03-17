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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The persistent class for the TB_SINGLE_SOURCE database table.
 *
 */
@Entity
@Table(name = "TB_SINGLE_SOURCE")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "SingleSource.findAll", query = "SELECT s FROM SingleSource s"),
    @NamedQuery(name = "SingleSource.getById", query = "SELECT sg FROM SingleSource sg "
            + "LEFT JOIN FETCH sg.singleSourceAttributes sa "
            + "LEFT JOIN FETCH sa.attribute l WHERE sg.id = :id"),
    @NamedQuery(name = "SingleSource.getByAttributeId", query = "SELECT sg FROM SingleSource sg "
            + "LEFT JOIN FETCH sg.singleSourceAttributes sa "
            + "LEFT JOIN FETCH sa.attribute l WHERE sa.attribute.id = :id"),
    @NamedQuery(name = "SingleSource.getByIds", query = "SELECT s FROM SingleSource s WHERE id in(:ids)")

})
public class SingleSource extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_SINGLE_SOURCE")
    private Long id;

    @Column(name = "DS_SINGLE_SOURCE")
    private String description;

    @Column(name = "NM_SINGLE_SOURCE")
    private String name;

    @Column(name = "TP_SINGLE_SOURCE")
    @Enumerated(EnumType.STRING)
    private SingleSourceTypeEnum type;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_SINGLE_SOURCE")
    private List<SingleSourceAttribute> singleSourceAttributes;

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

    public List<SingleSourceAttribute> getSingleSourceAttributes() {
        return singleSourceAttributes;
    }

    public void setSingleSourceAttributes(List<SingleSourceAttribute> singleSourceAttributes) {
        this.singleSourceAttributes = singleSourceAttributes;
    }

}
