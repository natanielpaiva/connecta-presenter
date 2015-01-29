package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisAS;
import br.com.cds.connecta.presenter.entity.Analysis;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;

@Service
public class AnalysisAS extends AbstractBaseAS<Analysis> implements IAnalysisAS {

    @Autowired
    private IAnalysisDAO analysisDAO;

    @Override
    public Analysis get(Long id) throws Exception {
        return analysisDAO.get(id);
    }
    
    @Override
    public List<Analysis> list() throws Exception {
        return analysisDAO.list();
    }

    @Override
    public Analysis saveOrUpdate(Analysis analysi) throws Exception {
        return analysisDAO.saveOrUpdate(analysi);
    }

    @Override
    public void delete(Long id) throws Exception {
        analysisDAO.delete(id);
    }

    @Override
    public void delete(Analysis analysi) throws Exception {
        analysisDAO.delete(analysi);
    }

}
