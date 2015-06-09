package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.AnalysisViewer;
import java.util.List;

/**
 *
 * @author Nataniel Paiva
 */
public interface IAnalysisViewerAS {

    AnalysisViewer get(Long id);

    List<AnalysisViewer> list() throws Exception;

    AnalysisViewer saveOrUpdate(AnalysisViewer entity) throws Exception;

    void delete(Long id) throws Exception;
    
    
}
