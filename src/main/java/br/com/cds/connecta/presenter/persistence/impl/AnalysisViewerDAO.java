package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.AnalysisViewer;
import br.com.cds.connecta.presenter.persistence.IAnalysisViewerDAO;

@Repository
public class AnalysisViewerDAO extends AbstractBaseJpaDAO<AnalysisViewer> implements IAnalysisViewerDAO{

    @Override
    public AnalysisViewer getByIdViewer(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
