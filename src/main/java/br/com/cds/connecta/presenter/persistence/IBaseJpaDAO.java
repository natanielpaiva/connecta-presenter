package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;

public interface IBaseJpaDAO<E extends AbstractBaseEntity> {

    E saveOrUpdate(E entity);

    void delete(Long id);

    void delete(E entity);

    E get(Long id);

}
