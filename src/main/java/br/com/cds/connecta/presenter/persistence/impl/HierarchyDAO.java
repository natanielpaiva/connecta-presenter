package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;
import br.com.cds.connecta.presenter.persistence.IHierarchyDAO;
import java.util.List;
import javax.persistence.Query;

@Repository
public class HierarchyDAO extends AbstractBaseJpaDAO<Hierarchy> implements IHierarchyDAO{

    @Override
    public List<Hierarchy> list() {
        Query query = getEntityManager().createNamedQuery("Hierarchy.findAll");
        List hierarchy = query.getResultList();
        return hierarchy;
    }

}
