package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IHierarchyAS;
import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;
import br.com.cds.connecta.presenter.persistence.IHierarchyDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HierarchyAS extends AbstractBaseAS<Hierarchy> implements IHierarchyAS {

    @Autowired
    private IHierarchyDAO dao;
    
    @PersistenceContext
    EntityManager em;

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
        Hierarchy h = dao.get(id);
        Hibernate.initialize(h.getHierarchyItem());
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
     *  deleta uma Hierarchy
     * @param id Long
     */
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public void delete(Hierarchy entity) {
        
    }

}
