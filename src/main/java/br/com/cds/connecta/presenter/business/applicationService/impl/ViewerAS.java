package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.business.applicationService.IViewerAS;
import br.com.cds.connecta.presenter.business.strategy.viewer.ViewerEntityInitializer;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import br.com.cds.connecta.presenter.persistence.ViewerRepository;
import br.com.cds.connecta.presenter.persistence.specification.ViewerSpecification;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class ViewerAS extends AbstractBaseAS<Viewer> implements IViewerAS {

    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    private ApplicationContext context;

    @Override
    public Viewer get(Long id, String domain, boolean initialize) {

        Viewer viewer = viewerRepository.findOne(ViewerSpecification.byIdAndDomain(id, domain));

        if (Util.isNull(viewer)) {
            throw new ResourceNotFoundException(Viewer.class.getSimpleName());
        }

        if (initialize) {
            ViewerEntityInitializer initializer = context.getBean(viewer.getType().getEntityInitializer());
            viewer = initializer.initializeViewerEntity(viewer);
        }

        return viewer;
    }
    
    @Override
    public Viewer getPublic(Long id, boolean initialize) {

        Viewer viewer = viewerRepository.findOne(ViewerSpecification.byId(id));

        if (Util.isNull(viewer)) {
            throw new ResourceNotFoundException(Viewer.class.getSimpleName());
        }

        if (initialize) {
            ViewerEntityInitializer initializer = context.getBean(viewer.getType().getEntityInitializer());
            viewer = initializer.initializeViewerEntity(viewer);
        }

        return viewer;
    }

    @Override
    public List<Viewer> list(String domain) {
        return viewerRepository.findAll(ViewerSpecification.byDomain(domain));
    }

    @Override
    public Viewer saveOrUpdate(Viewer entity) {
        return viewerRepository.save(entity);
    }

    @Override
    public void delete(Long id, String domain) {
        Viewer v = get(id, domain, false);
        viewerRepository.delete(v);
    }

    @Override
    public void deleteAll(List<Long> ids, String domain) {
        List<Viewer> listViewers = viewerRepository.findAll(ViewerSpecification.byIdsAndDomain(ids, domain));
        viewerRepository.delete(listViewers);
    }

}
