package br.com.cds.connecta.presenter.business.strategy.viewer;

import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import br.com.cds.connecta.presenter.entity.viewer.GraphViewer;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;

/**
 *
 * @author rsimplicio
 */
@Component
public class GraphViewerEntityInitializer implements ViewerEntityInitializer {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Viewer initializeViewerEntity(Viewer viewer) {
        TypedQuery<GraphViewer> query = em.createNamedQuery("GraphViewer.get", GraphViewer.class)
                .setParameter("id", viewer.getId());
        
        GraphViewer graphViewer = query.getSingleResult();
        
        if(Util.isNotNull(graphViewer.getAnalysis().getDatasource())){
	    	Datasource d = new Datasource();
	        d.setId(graphViewer.getAnalysis().getDatasource().getId());
	        d.setType(graphViewer.getAnalysis().getDatasource().getType());
	        graphViewer.getAnalysis().setDatasource(d);
    	}
        
        return graphViewer;
    }
}
