package br.com.cds.connecta.presenter.persistence;


import br.com.cds.connecta.presenter.entity.Viewer;
import java.util.List;

public interface IViewerDAO extends IBaseJpaDAO<Viewer> {
    
    Viewer get(Long id);
    
    List<Viewer> list();

}
