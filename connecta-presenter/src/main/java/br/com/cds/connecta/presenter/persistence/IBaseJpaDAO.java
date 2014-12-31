package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;

public interface IBaseJpaDAO<E extends AbstractBaseEntity> {
	
	public E saveOrUpdate(E entity);

	public void delete(Long id);

	public void delete(E entity);
	
	public E get(Long id);

}
