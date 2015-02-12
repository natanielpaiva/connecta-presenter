package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.persistence.ISingleSourceDAO;
import java.util.List;

@Repository
public class SingleSourceDAO extends AbstractBaseJpaDAO<SingleSource> implements ISingleSourceDAO {

    @Override
    public List<SingleSource> list() {
        return getEntityManager().createNamedQuery("SingleSource.findAll").getResultList();
    }

}
