/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.entity.hierarchy;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "TB_HIERARCHY")
@NamedQueries({
    @NamedQuery(name = "Hierarchy.findAll", query = "SELECT t FROM Hierarchy t")
})
@DynamicInsert(true)
@DynamicUpdate(true)
public class Hierarchy extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_HIERARCHY")
    private Long id;

    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_HIERARCHY", nullable = false)
    private List<HierarchyItem> hierarchyItem;
    
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

    public List<HierarchyItem> getHierarchyItem() {
        return hierarchyItem;
    }

    public void setHierarchyItem(List<HierarchyItem> HierarchyItem) {
        this.hierarchyItem = HierarchyItem;
    }

}
