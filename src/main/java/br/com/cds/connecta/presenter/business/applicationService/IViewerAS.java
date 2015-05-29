package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.Viewer;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nataniel Paiva
 */
public interface IViewerAS {

    Viewer get(Long id);

    List<Viewer> list() throws Exception;

    Viewer saveOrUpdate(Viewer entity) throws Exception;

    void delete(Long id) throws Exception;

    void delete(Viewer group) throws Exception;
    
    void teste()throws SQLException;
    
}
