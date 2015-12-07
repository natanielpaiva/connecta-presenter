package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import static br.com.cds.connecta.framework.core.util.Util.isNull;
import br.com.cds.connecta.presenter.business.applicationService.IHierarchyAS;
import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;
import br.com.cds.connecta.presenter.persistence.IHierarchyDAO;
import br.com.cds.connecta.presenter.persistence.impl.BulkActionsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HierarchyAS extends AbstractBaseAS<Hierarchy> implements IHierarchyAS {

    @Autowired
    private BulkActionsRepository bulk;

    @Autowired
    private IHierarchyDAO dao;

    /**
     *
     * @param hierarchy
     * @return
     */
    @Override
    public Hierarchy saveOrUpdate(Hierarchy hierarchy) {
        return dao.saveOrUpdate(hierarchy);
    }

    /**
     *
     * @param id Long
     * @return
     */
    @Override
    public Hierarchy get(Long id) {
        Hierarchy h;

        h = dao.get(id);

        if (isNull(h)) {
            throw new ResourceNotFoundException(Hierarchy.class.getCanonicalName());
        }

        return h;
    }

    /**
     *
     * @return List Hierarchy
     */
    @Override
    public List<Hierarchy> list() {
        List<Hierarchy> list = dao.list();
        return list;
    }

    /**
     * deleta uma Hierarchy
     *
     * @param id Long
     */
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public void delete(Hierarchy entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteAll(List<Long> ids) {
        bulk.delete(Hierarchy.class, ids);
    }

}
