package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.Viewer;
import br.com.cds.connecta.presenter.persistence.IViewerDAO;
import java.util.List;

@Repository
public class ViewerDAO extends AbstractBaseJpaDAO<Viewer> implements IViewerDAO{

    @Override
    public Viewer get(Long id) {
        return (Viewer) getEntityManager().createNamedQuery("Viewer.getById")
                .setParameter("id", id).getSingleResult();
    }
    
    public List<Viewer> list(){
        return getEntityManager().createNamedQuery("Viewer.findAll").getResultList();
    }

}
