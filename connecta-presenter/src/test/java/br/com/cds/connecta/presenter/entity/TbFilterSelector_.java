package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.260-0200")
@StaticMetamodel(FilterSelector.class)
public class TbFilterSelector_ {
	public static volatile SingularAttribute<FilterSelector, Long> pkFilterSelector;
	public static volatile SingularAttribute<FilterSelector, String> nmFilter;
	public static volatile SingularAttribute<FilterSelector, String> tpIcon;
	public static volatile SingularAttribute<FilterSelector, String> tpOperator;
	public static volatile ListAttribute<FilterSelector, PageFilter> tbPageFilters;
	public static volatile ListAttribute<FilterSelector, TbViewerFilter> tbViewerFilters;
}
