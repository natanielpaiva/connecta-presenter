package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.Group;
import java.util.List;

/**
 *
 * @author Nataniel Paiva
 */
public interface IGroupAS {

    Group get(Long id, String domain);

    List<Group> list(String domain);

    Group saveOrUpdate(Group entity);

    void delete(Long id, String domain);

    void delete(Group group);
   
    void preValidate(Group group);
    
    Group getSingleSourceByGroupId(Long id);

    void deleteAll(List<Long> ids, String domain);
    
}
