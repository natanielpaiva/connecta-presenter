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
import br.com.cds.connecta.presenter.domain.TypeGroupEnum;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The persistent class for the TB_GROUP database table.
 *
 */
@Entity
@Table(name = "TB_GROUP")
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Group.findAll", query = "SELECT t FROM Group t"),
    @NamedQuery(name = "Group.getByWhitSingleSourceId", query = "SELECT g FROM Group g "
            + "LEFT JOIN FETCH g.singleSourceGroup sgg "
            + "LEFT JOIN FETCH sgg.singleSource k "
            + "WHERE g.id = :id"),
    @NamedQuery(name = "Group.getByWhitAttributeId", query = "SELECT g FROM Group g "
            + "LEFT JOIN FETCH g.groupAttribute sgg "
            + "LEFT JOIN FETCH sgg.attribute k "
            + "WHERE g.id = :id"),
    @NamedQuery(name = "Group.getSingleSourceByGroupId", query = "SELECT g FROM Group g "
            + "INNER JOIN FETCH g.singleSourceGroup ssg "
            + "INNER JOIN FETCH ssg.singleSource sg "
            + "WHERE g.id = :id")
})
public class Group extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_GROUP_SEQ", sequenceName = "TB_GROUP_SEQ", 
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_GROUP_SEQ")
    @Column(name = "PK_GROUP")
    private Long id;

    @Column(name = "DS_GROUP")
    private String description;

    @Column(name = "NM_GROUP")
    private String name;

    @Column(name = "TP_GROUP")
    @Enumerated(EnumType.STRING)
    private TypeGroupEnum type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_QUERY")
    private Query query;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_GROUP")
    private List<SingleSourceGroup> singleSourceGroup;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_GROUP")
    private List<GroupAttribute> groupAttribute;

    /**
     *
     * @return
     */
    @Override
    public Long getId() {
        return id;
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

    public TypeGroupEnum getType() {
        return type;
    }

    public void setType(TypeGroupEnum type) {
        this.type = type;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
    
    public List<SingleSourceGroup> getSingleSourceGroup() {
        return singleSourceGroup;
    }

    public void setSingleSourceGroup(List<SingleSourceGroup> singleSourceGroup) {
        this.singleSourceGroup = singleSourceGroup;
    }

    public List<GroupAttribute> getGroupAttribute() {
        return groupAttribute;
    }

    public void setGroupAttribute(List<GroupAttribute> groupAttribute) {
        this.groupAttribute = groupAttribute;
    }
    
}
