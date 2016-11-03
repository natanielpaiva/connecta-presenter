package br.com.cds.connecta.presenter.business.applicationService.impl;

import static br.com.cds.connecta.framework.core.util.Util.isNotEmpty;
import static br.com.cds.connecta.framework.core.util.Util.isNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.business.applicationService.IGroupAS;
import br.com.cds.connecta.presenter.business.applicationService.ISingleSourceAS;
import br.com.cds.connecta.presenter.entity.Attribute;
import br.com.cds.connecta.presenter.entity.Group;
import br.com.cds.connecta.presenter.entity.GroupAttribute;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.SingleSourceGroup;
import br.com.cds.connecta.presenter.persistence.GroupRepository;
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
    private ISingleSourceAS singleSourceAS;

    @PersistenceContext
    private EntityManager entityManager;
    
     
    @Override
    public Group get(Long id,String domain) {
        Group group = groupRepository.findOne
        (GroupSpecification.byIdAndDomain(id, domain));
        
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
        refreshAttribute(group);
        refreshSingleSource(group);
        return groupRepository.save(group);
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
        return groupRepository.getSingleSourceByGroupId(id);
    }

    @Override
    public void deleteAll(List<Long> ids, String domain) {
    	List<Group> listGroups = 
    			groupRepository.findAll(GroupSpecification.byIdsAndDomain(ids, domain));
        groupRepository.delete(listGroups);
    }
    
    @Override
    public void refreshAttribute(Group group) {
        if (isNotEmpty(group.getGroupAttribute())) {
            for (GroupAttribute groupAttribute : group.getGroupAttribute()) {
                if (isNotNull(groupAttribute.getAttribute()) && 
                        isNotNull(groupAttribute.getAttribute().getId())) {
                    Attribute merge = entityManager
                            .merge(groupAttribute.getAttribute());
                    groupAttribute.setAttribute(merge);
                }
            }
        }
    }

    @Override
    public void refreshSingleSource(Group group) {
        if(isNotEmpty(group.getSingleSourceGroup())){
            for( SingleSourceGroup singleSourceGroup : group.getSingleSourceGroup() ){
                 if (isNotNull(singleSourceGroup.getSingleSource()) && 
                        isNotNull(singleSourceGroup.getSingleSource().getId())) {
                    SingleSource singleSource = singleSourceAS
                            .getWithAttributes(singleSourceGroup.getSingleSource()
                            .getId());
                    singleSourceGroup.setSingleSource(singleSource);
                    
                }
            }
        }
    }
    
}
