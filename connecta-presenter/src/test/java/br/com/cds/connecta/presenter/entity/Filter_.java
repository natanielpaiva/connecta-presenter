package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.189-0200")
@StaticMetamodel(Filter.class)
public class Filter_ {
	public static volatile SingularAttribute<Filter, Long> pkFilter;
	public static volatile SingularAttribute<Filter, String> nmFilter;
	public static volatile SingularAttribute<Filter, String> tpFilter;
	public static volatile SingularAttribute<Filter, String> txtColumnLabel;
	public static volatile SingularAttribute<Filter, String> txtColumnValue;
	public static volatile SingularAttribute<Filter, String> txtSelectedValue;
	public static volatile SingularAttribute<Filter, Analysi> tbAnalysi;
	public static volatile SingularAttribute<Filter, Layer> tbLayer;
	public static volatile ListAttribute<Filter, FilterValue> tbFilterValues;
	public static volatile ListAttribute<Filter, PageFilter> tbPageFilters;
	public static volatile ListAttribute<Filter, TbViewerFilter> tbViewerFilters;
}
