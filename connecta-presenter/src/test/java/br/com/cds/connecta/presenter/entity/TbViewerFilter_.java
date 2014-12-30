package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.281-0200")
@StaticMetamodel(TbViewerFilter.class)
public class TbViewerFilter_ {
	public static volatile SingularAttribute<TbViewerFilter, Long> pkViewerFilter;
	public static volatile SingularAttribute<TbViewerFilter, String> nmColumn;
	public static volatile SingularAttribute<TbViewerFilter, String> txtSelectedValue;
	public static volatile SingularAttribute<TbViewerFilter, Filter> filter;
	public static volatile SingularAttribute<TbViewerFilter, FilterSelector> tbFilterSelector;
	public static volatile SingularAttribute<TbViewerFilter, TbViewer> tbViewer;
}
