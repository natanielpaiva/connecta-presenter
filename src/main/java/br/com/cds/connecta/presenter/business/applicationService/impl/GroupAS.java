package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IGroupAS;
import br.com.cds.connecta.presenter.entity.Group;
import br.com.cds.connecta.presenter.persistence.IGroupDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class GroupAS extends AbstractBaseAS<Group> implements IGroupAS{

    @Autowired
    private IGroupDAO groupDao;
    
    
    @Override
    public Group get(Long id) {
        return groupDao.get(id);
    }

    @Override
    public List<Group> list() throws Exception {
        return groupDao.list();
    }

    @Override
    public Group saveOrUpdate(Group group) throws Exception {
        groupDao.refreshAttribute(group);
        groupDao.refreshSingleSource(group);
        return groupDao.saveOrUpdate(group);
    }

    @Override
    public void delete(Long id) throws Exception {
        groupDao.delete(id);
    }

    @Override
    public void delete(Group entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preValidate(Group group) {
        
    }

    @Override
    public Group getSingleSourceByGroupId(Long id) {
        return groupDao.getSingleSourceByGroupId(id);
    }
    
}
