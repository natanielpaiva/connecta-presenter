package br.com.cds.connecta.presenter.persistence.impl;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.hierarchy.HierarchyItem;
import br.com.cds.connecta.presenter.persistence.IHierarchyItemDAO;

@Repository
public class HierarchyItemDAO extends AbstractBaseJpaDAO<HierarchyItem> implements IHierarchyItemDAO{

}

