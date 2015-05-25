package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IViewerAS;
import br.com.cds.connecta.presenter.entity.Viewer;
import br.com.cds.connecta.presenter.persistence.IViewerDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class ViewerAS extends AbstractBaseAS<Viewer> implements IViewerAS{
    
    @Autowired
    private IViewerDAO viewerDao;

    @Override
    public Viewer get(Long id){
        return viewerDao.get(id);
    }

    @Override
    public List<Viewer> list() throws Exception {
        return viewerDao.list();
    }

    @Override
    public Viewer saveOrUpdate(Viewer entity) throws Exception {
        return viewerDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(Long id) throws Exception {
        viewerDao.delete(id);
    }

    @Override
    public void delete(Viewer entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
