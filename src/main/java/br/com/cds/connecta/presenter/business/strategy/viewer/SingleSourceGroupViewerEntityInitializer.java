package br.com.cds.connecta.presenter.business.strategy.viewer;

import br.com.cds.connecta.presenter.entity.viewer.SingleSourceGroupViewer;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

@Component
public class SingleSourceGroupViewerEntityInitializer implements ViewerEntityInitializer {

    @Override
    public Viewer initializeViewerEntity(Viewer viewer) {
        Hibernate.initialize( ((SingleSourceGroupViewer) viewer).getGroup() );
        return viewer;
    }

}
