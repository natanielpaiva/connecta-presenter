/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.entity.hierarchy;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import java.io.Serializable;
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
@Table(name = "TB_HIERARCHY_ITEM")
//@NamedQuery(
//         name="HierarchyItem.insert", 
//        query="insert into HierarchyItem (id, name, formula) VALUES (:id , :name, :formula)")
//@DynamicInsert(true)
//@DynamicUpdate(true)
public class HierarchyItem extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "TB_HIERARCHY_ITEM_SEQ",
            sequenceName = "TB_HIERARCHY_ITEM_SEQ",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_HIERARCHY_ITEM_SEQ")
    @Column(name = "PK_HIERARCHY_ITEM")
    private Long id;
    
    @Size(min = 1, max = 200)
    @Column(name = "COLUMN_NAME")
    private String name;

    @Size(max = 500)
    @Column(name = "COLUMN_FORMULA")
    private String formula;

    @Size(max = 100)
    @Column(name = "PATH_HIERARCHY")
    private String path;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "FK_HIERARCHY_ITEM_PARENT")
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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<HierarchyItem> getHierarchyItem() {
        return hierarchyItem;
    }

    public void setHierarchyItem(List<HierarchyItem> hierarchyItem) {
        this.hierarchyItem = hierarchyItem;
    }
}