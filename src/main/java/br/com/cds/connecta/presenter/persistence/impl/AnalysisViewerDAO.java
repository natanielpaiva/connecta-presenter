package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.AnalysisViewer;
import br.com.cds.connecta.presenter.persistence.IAnalysisViewerDAO;
import java.util.List;

@Repository
public class AnalysisViewerDAO extends AbstractBaseJpaDAO<AnalysisViewer> implements IAnalysisViewerDAO{

    @Override
    public AnalysisViewer get(Long id) {
        return (AnalysisViewer) getEntityManager().createNamedQuery("AnalysisViewer.get")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public AnalysisViewer getWithViewer(Long id) {
        return (AnalysisViewer) getEntityManager().createNamedQuery("AnalysisViewer.getWithViewer")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<AnalysisViewer> list() {
        return getEntityManager().createNamedQuery("AnalysisViewer.findAll")
                .getResultList();
 
    }
    

}
