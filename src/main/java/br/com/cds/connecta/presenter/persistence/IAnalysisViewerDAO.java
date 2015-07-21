package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.AnalysisViewer;
import java.util.List;

public interface IAnalysisViewerDAO extends IBaseJpaDAO<AnalysisViewer> {

    @Override
    AnalysisViewer get(Long id);
    
    AnalysisViewer getWithViewer(Long id);
    
    @Override
    public void delete(Long id);
    
    List<AnalysisViewer> list();

}
