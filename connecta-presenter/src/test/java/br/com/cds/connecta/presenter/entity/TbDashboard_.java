package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.248-0200")
@StaticMetamodel(Dashboard.class)
public class TbDashboard_ {
	public static volatile SingularAttribute<Dashboard, Long> pkDashboard;
	public static volatile SingularAttribute<Dashboard, String> dsDashboard;
	public static volatile SingularAttribute<Dashboard, String> flgShow;
	public static volatile SingularAttribute<Dashboard, String> nmDashboard;
	public static volatile ListAttribute<Dashboard, Page> tbPages;
}
