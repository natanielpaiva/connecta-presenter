package br.com.cds.connecta.presenter.persistence;


import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import java.util.List;

public interface IViewerDAO extends IBaseJpaDAO<Viewer> {
    
    List<Viewer> list();

}
