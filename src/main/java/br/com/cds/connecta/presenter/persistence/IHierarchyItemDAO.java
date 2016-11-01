package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.hierarchy.HierarchyItem;

public interface IHierarchyItemDAO extends IBaseJpaDAO<HierarchyItem> {
    
    HierarchyItem saveOrUpdate(HierarchyItem hierarchyItem);

}
