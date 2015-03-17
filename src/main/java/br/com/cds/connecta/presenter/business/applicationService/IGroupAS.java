package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.Group;
import java.util.List;

/**
 *
 * @author Nataniel Paiva
 */
public interface IGroupAS {

    Group get(Long id);

    List<Group> list() throws Exception;

    Group saveOrUpdate(Group entity) throws Exception;

    void delete(Long id) throws Exception;

    void delete(Group group) throws Exception;
   
    void preValidate(Group group);
    
}
