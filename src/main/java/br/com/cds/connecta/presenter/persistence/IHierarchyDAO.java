package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;

public interface IHierarchyDAO extends IBaseJpaDAO<Hierarchy> {

    public abstract List<Hierarchy> list();

}
