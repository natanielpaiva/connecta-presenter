package br.com.cds.connecta.presenter.business.strategy.viewer;

import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import org.springframework.stereotype.Component;

@Component
public class TwitterTimelineViewerEntityInitializer implements ViewerEntityInitializer {

    @Override
    public Viewer initializeViewerEntity(Viewer viewer) {
        return viewer;
    }

}
