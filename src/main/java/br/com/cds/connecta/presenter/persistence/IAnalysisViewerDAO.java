package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;
import java.util.List;

public interface IAnalysisViewerDAO extends IBaseJpaDAO<AnalysisViewer> {
    
    List<AnalysisViewer> list();

}
