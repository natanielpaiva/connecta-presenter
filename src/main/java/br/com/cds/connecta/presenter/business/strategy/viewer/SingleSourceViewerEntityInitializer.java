package br.com.cds.connecta.presenter.business.strategy.viewer;

import br.com.cds.connecta.presenter.entity.viewer.SingleSourceViewer;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class SingleSourceViewerEntityInitializer implements ViewerEntityInitializer {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Viewer initializeViewerEntity(Viewer viewer) {
        TypedQuery<SingleSourceViewer> query = em.createNamedQuery("SingleSourceViewer.get", SingleSourceViewer.class)
                .setParameter("id", viewer.getId());
        
        return query.getSingleResult();
    }

}
