package br.com.cds.connecta.presenter.business.strategy.viewer;

import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
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
        //Necessário limpar os dados do datasource para não mostrar no frontend
    	if(Util.isNotNull(analysisViewer.getAnalysis().getDatasource())){
	    	Datasource d = new Datasource();
	        d.setId(analysisViewer.getAnalysis().getDatasource().getId());
	        d.setType(analysisViewer.getAnalysis().getDatasource().getType());
	        analysisViewer.getAnalysis().setDatasource(d);
    	}
        Hibernate.initialize(analysisViewer.getAnalysis().getAnalysisColumns());
        
        return analysisViewer;
    }

}
