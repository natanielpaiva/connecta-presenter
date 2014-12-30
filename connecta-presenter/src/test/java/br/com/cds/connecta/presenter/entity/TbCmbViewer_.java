package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.242-0200")
@StaticMetamodel(CmbViewer.class)
public class TbCmbViewer_ {
	public static volatile SingularAttribute<CmbViewer, Long> pkCmbViewer;
	public static volatile ListAttribute<CmbViewer, ChildViewer> taChildViewers;
	public static volatile SingularAttribute<CmbViewer, Analysi> tbAnalysi;
	public static volatile SingularAttribute<CmbViewer, CombinedAnalysi> tbCombinedAnalysi;
	public static volatile SingularAttribute<CmbViewer, TbViewer> tbViewer;
}
