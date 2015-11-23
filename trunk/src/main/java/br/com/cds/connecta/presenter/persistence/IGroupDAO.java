package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.Group;
import java.util.List;

/**
 *
 * @author Nataniel Paiva
 */
public interface IGroupDAO extends IBaseJpaDAO<Group> {

    List<Group> list();
    
    void refreshAttribute(Group group);
    
    /**
     *
     * @param group
     */
    void refreshSingleSource(Group group);
    
    Group getSingleSourceByGroupId(Long id);

}
