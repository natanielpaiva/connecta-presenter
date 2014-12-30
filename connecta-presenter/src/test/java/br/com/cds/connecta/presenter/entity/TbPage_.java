package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.272-0200")
@StaticMetamodel(Page.class)
public class TbPage_ {
	public static volatile SingularAttribute<Page, Long> pkPage;
	public static volatile SingularAttribute<Page, String> dsPage;
	public static volatile SingularAttribute<Page, String> flgInfinityScroll;
	public static volatile SingularAttribute<Page, String> nmPage;
	public static volatile SingularAttribute<Page, BigDecimal> nuColumns;
	public static volatile SingularAttribute<Page, BigDecimal> nuGitter;
	public static volatile SingularAttribute<Page, BigDecimal> nuLines;
	public static volatile SingularAttribute<Page, BigDecimal> nuOrder;
	public static volatile SingularAttribute<Page, BigDecimal> nuTransitionTime;
	public static volatile SingularAttribute<Page, String> tpEffect;
	public static volatile SingularAttribute<Page, String> tpPage;
	public static volatile ListAttribute<Page, Container> taContainers;
	public static volatile SingularAttribute<Page, Dashboard> tbDashboard;
	public static volatile ListAttribute<Page, PageFilter> tbPageFilters;
}
