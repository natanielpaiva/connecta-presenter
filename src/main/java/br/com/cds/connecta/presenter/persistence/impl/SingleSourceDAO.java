package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import static br.com.cds.connecta.framework.core.util.Util.isNotNull;
import br.com.cds.connecta.presenter.entity.Attribute;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.SingleSourceAttribute;
import br.com.cds.connecta.presenter.persistence.ISingleSourceDAO;
import java.util.List;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Repository
public class SingleSourceDAO extends AbstractBaseJpaDAO<SingleSource> implements ISingleSourceDAO {

    @Override
    public List<SingleSource> list() {
        return getEntityManager().createNamedQuery("SingleSource.findAll").getResultList();
    }

    @Override
    public SingleSource get(Long id) {
        return (SingleSource) getEntityManager()
                .createNamedQuery("SingleSource.getById").setParameter("id", id).getSingleResult();
    }

    @Override
    public void refreshAttribute(SingleSource singleSource) {
        if (isNotEmpty(singleSource.getSingleSourceAttributes())) {
            for (SingleSourceAttribute singleSourceAttribute : singleSource.getSingleSourceAttributes()) {
                if (isNotNull(singleSourceAttribute.getAttribute()) && isNotNull(singleSourceAttribute.getAttribute().getId())) {
                    Attribute merge = getEntityManager().merge(singleSourceAttribute.getAttribute());
                    singleSourceAttribute.setAttribute(merge);
                }
            }
        }
    }

}
