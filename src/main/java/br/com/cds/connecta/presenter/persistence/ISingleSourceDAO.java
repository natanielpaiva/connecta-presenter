package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import br.com.cds.connecta.presenter.entity.SingleSource;

public interface ISingleSourceDAO extends IBaseJpaDAO<SingleSource>{

	public abstract List<SingleSource> list();

}