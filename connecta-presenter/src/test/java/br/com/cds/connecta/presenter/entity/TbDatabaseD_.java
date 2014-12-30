package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.250-0200")
@StaticMetamodel(DatabaseD.class)
public class TbDatabaseD_ {
	public static volatile SingularAttribute<DatabaseD, Long> pkDatabaseDs;
	public static volatile SingularAttribute<DatabaseD, String> cdDriver;
	public static volatile SingularAttribute<DatabaseD, BigDecimal> txtPort;
	public static volatile SingularAttribute<DatabaseD, String> txtSchema;
	public static volatile SingularAttribute<DatabaseD, String> txtServer;
	public static volatile SingularAttribute<DatabaseD, String> txtSid;
	public static volatile SingularAttribute<DatabaseD, Datasource> tbDatasource;
}
