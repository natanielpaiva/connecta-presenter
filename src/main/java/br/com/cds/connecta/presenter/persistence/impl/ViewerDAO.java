package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.business.strategy.viewer.ViewerEntityInitializer;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import br.com.cds.connecta.presenter.persistence.IViewerDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Repository
public class ViewerDAO extends AbstractBaseJpaDAO<Viewer> implements IViewerDAO {
    
    @Autowired
    private ApplicationContext context;

    @Override
    public Viewer get(Long id) {
        Viewer viewer = (Viewer) getEntityManager().createNamedQuery("Viewer.getById")
                .setParameter("id", id).getSingleResult();
        
        ViewerEntityInitializer initializer = context.getBean(viewer.getType().getEntityInitializer());
        
        return initializer.initializeViewerEntity(viewer);
    }
    
    @Override
    public List<Viewer> list(){
        return getEntityManager().createNamedQuery("Viewer.findAll").getResultList();
    }

}
