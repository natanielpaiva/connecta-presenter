package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.252-0200")
@StaticMetamodel(Datasource.class)
public class TbDatasource_ {
	public static volatile SingularAttribute<Datasource, Long> pkDatasource;
	public static volatile SingularAttribute<Datasource, String> dsDatasource;
	public static volatile SingularAttribute<Datasource, String> nmDatasource;
	public static volatile SingularAttribute<Datasource, String> tpDatasource;
	public static volatile SingularAttribute<Datasource, String> txtSenha;
	public static volatile SingularAttribute<Datasource, String> txtUsuario;
	public static volatile ListAttribute<Datasource, Analysi> tbAnalysis;
	public static volatile ListAttribute<Datasource, BiD> tbBiDs;
	public static volatile ListAttribute<Datasource, DatabaseD> tbDatabaseDs;
	public static volatile ListAttribute<Datasource, EndecaD> tbEndecaDs;
	public static volatile ListAttribute<Datasource, HdfsD> tbHdfsDs;
	public static volatile ListAttribute<Datasource, SolrD> tbSolrDs;
	public static volatile ListAttribute<Datasource, TbWebserviceD> tbWebserviceDs;
}
