package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;
import java.util.List;

/**
 *
 * @author diego
 */
public interface IHierarchyAS {

    Hierarchy get(Long id);

    List<Hierarchy> list();

    Hierarchy saveOrUpdate(Hierarchy entity);

    void delete(Long id);

    void deleteAll(List<Long> ids);

}
