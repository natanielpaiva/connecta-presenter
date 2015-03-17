package br.com.cds.connecta.presenter.persistence.impl;

import br.com.cds.connecta.presenter.persistence.IGroupDAO;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import static br.com.cds.connecta.framework.core.util.Util.isNotEmpty;
import static br.com.cds.connecta.framework.core.util.Util.isNotNull;
import br.com.cds.connecta.presenter.entity.Attribute;
import br.com.cds.connecta.presenter.entity.Group;
import br.com.cds.connecta.presenter.entity.GroupAttribute;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.SingleSourceGroup;
import java.util.List;
import javax.persistence.EntityManager;

@Repository
public class GroupDAO extends AbstractBaseJpaDAO<Group> implements IGroupDAO {

    @Override
    public List<Group> list() {
        return getEntityManager().createNamedQuery("Group.findAll").getResultList();
    }

    public Group get(Long id) {
        Group groupWithSingleSource = (Group) getEntityManager()
                .createNamedQuery("Group.getByWhitSingleSourceId")
                .setParameter("id", id).getSingleResult();
        Group groupWithAttribute = (Group) getEntityManager()
                .createNamedQuery("Group.getByWhitAttributeId")
                .setParameter("id", id).getSingleResult();
        
        groupWithAttribute.setSingleSourceGroup(groupWithSingleSource.getSingleSourceGroup());
        
        return  groupWithAttribute;
    }

    @Override
    public void refreshAttribute(Group group) {
        if (isNotEmpty(group.getGroupAttribute())) {
            for (GroupAttribute groupAttribute : group.getGroupAttribute()) {
                if (isNotNull(groupAttribute.getAttribute()) && 
                        isNotNull(groupAttribute.getAttribute().getId())) {
                    Attribute merge = getEntityManager()
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
                    SingleSource merge = getEntityManager()
                            .merge(singleSourceGroup.getSingleSource());
                    singleSourceGroup.setSingleSource(merge);
                    
                }
            }
        }
    }

}
