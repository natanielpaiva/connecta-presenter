package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.273-0200")
@StaticMetamodel(PageFilter.class)
public class TbPageFilter_ {
	public static volatile SingularAttribute<PageFilter, Long> pkPageFilter;
	public static volatile SingularAttribute<PageFilter, String> nmColumn;
	public static volatile SingularAttribute<PageFilter, String> txtSelectedValue;
	public static volatile SingularAttribute<PageFilter, Filter> filter;
	public static volatile SingularAttribute<PageFilter, FilterSelector> tbFilterSelector;
	public static volatile SingularAttribute<PageFilter, Page> tbPage;
}
