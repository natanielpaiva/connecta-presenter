package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.224-0200")
@StaticMetamodel(Container.class)
public class TaContainer_ {
	public static volatile SingularAttribute<Container, Long> pkContainer;
	public static volatile SingularAttribute<Container, BigDecimal> nuHspace;
	public static volatile SingularAttribute<Container, BigDecimal> nuLeft;
	public static volatile SingularAttribute<Container, BigDecimal> nuLength;
	public static volatile SingularAttribute<Container, BigDecimal> nuPosition;
	public static volatile SingularAttribute<Container, BigDecimal> nuTop;
	public static volatile SingularAttribute<Container, BigDecimal> nuVspace;
	public static volatile SingularAttribute<Container, BigDecimal> nuWidth;
	public static volatile SingularAttribute<Container, Page> tbPage;
	public static volatile SingularAttribute<Container, TbViewer> tbViewer;
}
