package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import java.util.List;

/**
 *
 * @author Nataniel Paiva
 */
public interface IViewerAS {
    
    Viewer get(Long id);

    List<Viewer> list();

    Viewer saveOrUpdate(Viewer entity);

    void delete(Long id);

    void delete(Viewer group);
}
