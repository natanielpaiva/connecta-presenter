package br.com.cds.connecta.presenter.business.strategy.viewer;

import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

@Component
public class AnalysisViewerEntityInitializer implements ViewerEntityInitializer {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Viewer initializeViewerEntity(Viewer viewer) {
        TypedQuery<AnalysisViewer> query = em.createNamedQuery("AnalysisViewer.get", AnalysisViewer.class)
                .setParameter("id", viewer.getId());
        
        AnalysisViewer analysisViewer = query.getSingleResult();
        
        Hibernate.initialize(analysisViewer.getAnalysis().getAnalysisColumns());
        
        return analysisViewer;
    }

}
