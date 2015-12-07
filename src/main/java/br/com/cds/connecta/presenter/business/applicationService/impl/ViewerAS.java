package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import br.com.cds.connecta.presenter.business.applicationService.IViewerAS;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import br.com.cds.connecta.presenter.persistence.IViewerDAO;
import br.com.cds.connecta.presenter.persistence.impl.BulkActionsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class ViewerAS extends AbstractBaseAS<Viewer> implements IViewerAS {

    @Autowired
    private IViewerDAO dao;
    
    @Autowired
    private BulkActionsRepository<Long> bulk;
    
    @Override
    public Viewer get(Long id) {
        Viewer viewer;
        
        try {
            viewer = dao.get(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException(Viewer.class.getCanonicalName());
        }
        
        return viewer;
    }

    @Override
    public List<Viewer> list() {
        return dao.list();
    }

    @Override
    public Viewer saveOrUpdate(Viewer entity) {
        return dao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public void delete(Viewer entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteAll(List<Long> ids) {
        bulk.delete(Viewer.class, ids);
    }

}
