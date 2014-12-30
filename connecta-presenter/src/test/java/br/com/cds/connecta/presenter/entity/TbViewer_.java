package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.280-0200")
@StaticMetamodel(TbViewer.class)
public class TbViewer_ {
	public static volatile SingularAttribute<TbViewer, Long> pkViewer;
	public static volatile SingularAttribute<TbViewer, String> dsViewer;
	public static volatile SingularAttribute<TbViewer, String> nmViewer;
	public static volatile ListAttribute<TbViewer, ChildViewer> taChildViewers;
	public static volatile ListAttribute<TbViewer, Container> taContainers;
	public static volatile ListAttribute<TbViewer, AnalysisViewer> tbAnalysisViewers;
	public static volatile ListAttribute<TbViewer, CmbViewer> tbCmbViewers;
	public static volatile ListAttribute<TbViewer, LayerViewer> tbLayerViewers;
	public static volatile ListAttribute<TbViewer, SnglSrcViewer> tbSnglSrcViewers;
	public static volatile SingularAttribute<TbViewer, LayerViewer> tbLayerViewer;
	public static volatile ListAttribute<TbViewer, TbViewerFilter> tbViewerFilters;
}
