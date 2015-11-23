package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.persistence.IFileSingleSourceDAO;
import java.util.List;
@Repository
public class FileSingleSourceDAO extends AbstractBaseJpaDAO<FileSingleSource> implements IFileSingleSourceDAO{

    @Override
    public List<FileSingleSource> list() {
        return getEntityManager().createNamedQuery("FileSingleSource.findAll").getResultList();
    }

    @Override
    public FileSingleSource getWithBinary(Long id) {
        return (FileSingleSource) getEntityManager().createNamedQuery("FileSingleSource.getWithBinary")
                .setParameter("id", id).getSingleResult();
                
    }

}
