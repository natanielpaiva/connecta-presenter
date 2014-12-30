package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.264-0200")
@StaticMetamodel(HdfsAnalysi.class)
public class TbHdfsAnalysi_ {
	public static volatile SingularAttribute<HdfsAnalysi, Long> pkHdfsAnalysis;
	public static volatile SingularAttribute<HdfsAnalysi, String> txtHiveql;
	public static volatile SingularAttribute<HdfsAnalysi, String> txtMrClass;
	public static volatile SingularAttribute<HdfsAnalysi, String> txtMrMethod;
	public static volatile SingularAttribute<HdfsAnalysi, String> txtPigQuery;
	public static volatile SingularAttribute<HdfsAnalysi, Analysi> tbAnalysi;
}
