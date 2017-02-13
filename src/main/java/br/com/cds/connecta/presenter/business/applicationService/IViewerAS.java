package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import br.com.cds.connecta.presenter.filter.ViewerFilter;

import java.util.List;

/**
 *
 * @author Nataniel Paiva
 */
public interface IViewerAS {
    
    Viewer get(Long id, String domain, boolean initialize);
    
    Viewer getPublic(Long id, boolean initialize);

    Iterable<Viewer> list(ViewerFilter filter);

    Viewer saveOrUpdate(Viewer entity);

    void delete(Long id, String domain);

    void deleteAll(List<Long> ids, String domain);
}
