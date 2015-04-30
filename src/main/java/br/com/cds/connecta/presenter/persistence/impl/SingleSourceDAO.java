package br.com.cds.connecta.presenter.persistence.impl;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.FileSingleSource;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.SingleSourceGroup;
import br.com.cds.connecta.presenter.persistence.ISingleSourceDAO;
import java.util.List;

@Repository
public class SingleSourceDAO extends AbstractBaseJpaDAO<SingleSource> implements ISingleSourceDAO {
  

    @Override
    public List<SingleSource> getByAttributeId(Long id) {
        
        return getEntityManager().createNamedQuery("SingleSource.getByAttributeId")
                .setParameter("id", id).getResultList();
        
    }

    @Override
    public SingleSource getWithAttributes(Long id) {
        return (SingleSource) getEntityManager().createNamedQuery("SingleSource.getById")
                .setParameter("id", id).getSingleResult();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<SingleSourceGroup> findById(Long id) {
        return getEntityManager().createNamedQuery("SingleSourceGroup.findById")
                .setParameter("id", id).getResultList();
    }

    @Override
    public List<FileSingleSource> getByIds(List<Long> ids) {
        
        return getEntityManager().createNamedQuery("SingleSource.getByIds")
                .setParameter("ids", ids).getResultList();
    }


}
