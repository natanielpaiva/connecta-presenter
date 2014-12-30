package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.276-0200")
@StaticMetamodel(SingleSource.class)
public class TbSingleSource_ {
	public static volatile SingularAttribute<SingleSource, Long> pkSingleSource;
	public static volatile SingularAttribute<SingleSource, String> dsSingleSource;
	public static volatile SingularAttribute<SingleSource, String> nmSingleSource;
	public static volatile ListAttribute<SingleSource, AttrSnglSrc> taAttrSnglSrcs;
	public static volatile ListAttribute<SingleSource, SnglSrcGroup> taSnglSrcGroups;
	public static volatile ListAttribute<SingleSource, SnglSrcVwSsrc> taSnglSrcVwSsrcs;
	public static volatile ListAttribute<SingleSource, File> tbFiles;
	public static volatile ListAttribute<SingleSource, UrlWeb> tbUrlWebs;
}
