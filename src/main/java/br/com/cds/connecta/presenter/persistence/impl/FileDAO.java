package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.persistence.IFileDAO;
import java.util.List;
@Repository
public class FileDAO extends AbstractBaseJpaDAO<FileSingleSource> implements IFileDAO{

    @Override
    public List<FileSingleSource> list() {
        return getEntityManager().createNamedQuery("FileSingleSource.findAll").getResultList();
    }

}
