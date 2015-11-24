package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;
import br.com.cds.connecta.presenter.entity.hierarchy.HierarchyItem;
import java.util.List;

public interface IHierarchyItemDAO extends IBaseJpaDAO<HierarchyItem> {
    
    HierarchyItem saveOrUpdate(HierarchyItem hierarchyItem);

}
