package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.268-0200")
@StaticMetamodel(LayerSource.class)
public class TbLayerSource_ {
	public static volatile SingularAttribute<LayerSource, Long> idSource;
	public static volatile SingularAttribute<LayerSource, String> dsLinkExterno;
	public static volatile SingularAttribute<LayerSource, String> dsLinkInterno;
	public static volatile SingularAttribute<LayerSource, BigDecimal> idSourceType;
	public static volatile SingularAttribute<LayerSource, String> nmPassRest;
	public static volatile SingularAttribute<LayerSource, String> nmSource;
	public static volatile SingularAttribute<LayerSource, String> nmUserRest;
}
