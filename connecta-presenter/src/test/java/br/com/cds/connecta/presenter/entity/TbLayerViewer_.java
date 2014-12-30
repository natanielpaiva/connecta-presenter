package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.269-0200")
@StaticMetamodel(LayerViewer.class)
public class TbLayerViewer_ {
	public static volatile SingularAttribute<LayerViewer, Long> idViewer;
	public static volatile SingularAttribute<LayerViewer, String> dsParamNames;
	public static volatile SingularAttribute<LayerViewer, String> dsParamValues;
	public static volatile SingularAttribute<LayerViewer, BigDecimal> idLvImpl;
	public static volatile SingularAttribute<LayerViewer, BigDecimal> idTpViewer;
	public static volatile SingularAttribute<LayerViewer, String> nmViewer;
	public static volatile SingularAttribute<LayerViewer, Layer> tbLayer;
	public static volatile SingularAttribute<LayerViewer, TbViewer> tbViewer;
	public static volatile ListAttribute<LayerViewer, TbViewer> tbViewers;
}
