package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.business.applicationService.IGroupAS;
import br.com.cds.connecta.presenter.entity.Group;
import br.com.cds.connecta.presenter.persistence.GroupRepository;
import br.com.cds.connecta.presenter.persistence.IGroupDAO;
import br.com.cds.connecta.presenter.persistence.specification.GroupSpecification;

/**
 *
 * @author Nataniel Paiva
 */
@Service
public class GroupAS extends AbstractBaseAS<Group> implements IGroupAS{
    
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private IGroupDAO groupDao;
    
    @Override
    public Group get(Long id,String domain) {
        Group group = 
        		groupRepository.findOne(GroupSpecification.byIdAndDomain(id, domain));
        
        if(Util.isNull(group)){
            throw new ResourceNotFoundException(Group.class.getSimpleName());
        }
        
        return group;
    }

    @Override
    public List<Group> list(String domain) {
        return groupRepository.findAll(GroupSpecification.byDomain(domain));
    }

    @Override
    public Group saveOrUpdate(Group group) {
        groupDao.refreshAttribute(group);
        groupDao.refreshSingleSource(group);
        return groupDao.saveOrUpdate(group);
    }

    @Override
    public void delete(Long id, String domain) {
    	Group group = get(id, domain);
    	groupRepository.delete(group);
    }

    @Override
    public void delete(Group entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void preValidate(Group group) {
        
    }

    @Override
    public Group getSingleSourceByGroupId(Long id) {
        return groupDao.getSingleSourceByGroupId(id);
    }

    @Override
    public void deleteAll(List<Long> ids, String domain) {
    	List<Group> listGroups = 
    			groupRepository.findAll(GroupSpecification.byIdsAndDomain(ids, domain));
        groupRepository.delete(listGroups);
    }
    
}
