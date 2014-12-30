package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.267-0200")
@StaticMetamodel(Layer.class)
public class TbLayer_ {
	public static volatile SingularAttribute<Layer, Long> idLayer;
	public static volatile SingularAttribute<Layer, String> dsLayer;
	public static volatile SingularAttribute<Layer, BigDecimal> idSource;
	public static volatile SingularAttribute<Layer, String> nmLayer;
	public static volatile ListAttribute<Layer, Filter> filters;
	public static volatile ListAttribute<Layer, LayerViewer> tbLayerViewers;
}
