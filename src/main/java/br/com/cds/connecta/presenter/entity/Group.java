package br.com.cds.connecta.presenter.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.domain.GroupType;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;

/**
 * The persistent class for the TB_GROUP database table.
 *
 */
@Entity
@Table(name = "TB_GROUP")
@DynamicUpdate
public class Group extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_GROUP")
    private Long id;

    @Column(name = "DS_GROUP")
    private String description;

    @Column(name = "NM_GROUP")
    private String name;

    @Column(name = "TP_GROUP")
    @Enumerated(EnumType.STRING)
    private GroupType type;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_QUERY")
    private Query query;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_GROUP")
    private List<SingleSourceGroup> singleSourceGroup;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_GROUP")
    private List<GroupAttribute> groupAttribute;
    
    @Column(name = "NM_DOMAIN")
    private String domain;

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

    public GroupType getType() {
        return type;
    }

    public void setType(GroupType type) {
        this.type = type;
    }

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
    
    
}
