package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.266-0200")
@StaticMetamodel(HdfsD.class)
public class TbHdfsD_ {
	public static volatile SingularAttribute<HdfsD, Long> pkHdfsDs;
	public static volatile SingularAttribute<HdfsD, String> txtPath;
	public static volatile SingularAttribute<HdfsD, BigDecimal> txtPort;
	public static volatile SingularAttribute<HdfsD, String> txtServer;
	public static volatile SingularAttribute<HdfsD, Datasource> tbDatasource;
}
