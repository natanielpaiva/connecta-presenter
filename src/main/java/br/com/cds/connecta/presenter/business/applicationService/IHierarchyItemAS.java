package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.hierarchy.HierarchyItem;
import java.util.List;

/**
 *
 * @author diego
 */
public interface IHierarchyItemAS {

    List<HierarchyItem> getChildItems(Long id);

    HierarchyItem saveItem(HierarchyItem hierarchyItem, long idItemParent);

    HierarchyItem updateItem(HierarchyItem hierarchyItem);

    void delete(Long id);
}
