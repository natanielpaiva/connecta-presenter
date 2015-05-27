package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisAS;
import br.com.cds.connecta.presenter.entity.Analysis;
import br.com.cds.connecta.presenter.entity.AnalysisColumn;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;

@Service
public class AnalysisAS extends AbstractBaseAS<Analysis> implements IAnalysisAS {

    @Autowired
    private IAnalysisDAO analysisDAO;

    @Override
    public Analysis get(Long id) {
        return analysisDAO.get(id);
    }
    
    @Override
    public List<Analysis> list() {
        return analysisDAO.list();
    }

    @Override
    public Analysis saveOrUpdate(Analysis analysis) {
        return analysisDAO.saveOrUpdate(analysis);
    }

    @Override
    public void delete(Long id) {
        analysisDAO.delete(id);
    }

    @Override
    public void delete(Analysis analysi) {
        analysisDAO.delete(analysi);
    }

    @Override
    public List<AnalysisColumn> getColumns(Long id) {
        Analysis analysis = analysisDAO.get(id);
        
        //nao terminei ainda
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
