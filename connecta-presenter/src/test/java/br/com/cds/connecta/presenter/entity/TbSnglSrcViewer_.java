package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.277-0200")
@StaticMetamodel(SnglSrcViewer.class)
public class TbSnglSrcViewer_ {
	public static volatile SingularAttribute<SnglSrcViewer, Long> pkSnglSrcViewer;
	public static volatile SingularAttribute<SnglSrcViewer, String> tpOrganization;
	public static volatile SingularAttribute<SnglSrcViewer, String> tpViewer;
	public static volatile ListAttribute<SnglSrcViewer, SnglSrcVwGroup> taSnglSrcVwGroups;
	public static volatile ListAttribute<SnglSrcViewer, SnglSrcVwSsrc> taSnglSrcVwSsrcs;
	public static volatile SingularAttribute<SnglSrcViewer, TbViewer> tbViewer;
}
