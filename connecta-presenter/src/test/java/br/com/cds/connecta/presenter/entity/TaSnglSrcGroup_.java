package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.227-0200")
@StaticMetamodel(SnglSrcGroup.class)
public class TaSnglSrcGroup_ {
	public static volatile SingularAttribute<SnglSrcGroup, Long> pkSnglSrcGroup;
	public static volatile SingularAttribute<SnglSrcGroup, BigDecimal> nuOrder;
	public static volatile SingularAttribute<SnglSrcGroup, Group> tbGroup;
	public static volatile SingularAttribute<SnglSrcGroup, SingleSource> tbSingleSource;
}
