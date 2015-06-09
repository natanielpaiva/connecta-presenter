package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.AnalysisViewer;

public interface IAnalysisViewerDAO extends IBaseJpaDAO<AnalysisViewer> {

    AnalysisViewer get(Long id);
    
    AnalysisViewer getWithViewer(Long id);

}
