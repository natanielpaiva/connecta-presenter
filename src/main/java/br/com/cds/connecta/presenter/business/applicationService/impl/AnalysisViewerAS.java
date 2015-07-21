package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisViewerAS;
import br.com.cds.connecta.presenter.entity.AnalysisViewer;
import br.com.cds.connecta.presenter.persistence.IAnalysisViewerDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class AnalysisViewerAS extends AbstractBaseAS<AnalysisViewer> implements IAnalysisViewerAS {
    
    @Autowired
    private IAnalysisViewerDAO analysisViewerDao;
    
    @Override
    public AnalysisViewer get(Long id) {
        AnalysisViewer analysisViewer = analysisViewerDao.get(id);
        AnalysisViewer analysisWithViewer = analysisViewerDao.getWithViewer(id);
        analysisViewer.setViewer(analysisWithViewer.getViewer());
        return analysisViewer;
    }
    
    @Override
    public List<AnalysisViewer> list() throws Exception {
        return analysisViewerDao.list();
    }
    
    @Override
    public AnalysisViewer saveOrUpdate(AnalysisViewer entity) throws Exception {
        return analysisViewerDao.saveOrUpdate(entity);
    }
    
    @Override
    public void delete(Long id) throws Exception {
        analysisViewerDao.delete(id);
    }
    
    @Override
    public void delete(AnalysisViewer entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
