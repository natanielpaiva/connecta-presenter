package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.230-0200")
@StaticMetamodel(Analysi.class)
public class TbAnalysi_ {
	public static volatile SingularAttribute<Analysi, Long> pkAnalysis;
	public static volatile SingularAttribute<Analysi, String> dsAnalysis;
	public static volatile SingularAttribute<Analysi, String> nmAnalysis;
	public static volatile SingularAttribute<Analysi, String> tpAnalysis;
	public static volatile ListAttribute<Analysi, Filter> filters;
	public static volatile ListAttribute<Analysi, AttrAnalysi> taAttrAnalysis;
	public static volatile SingularAttribute<Analysi, Datasource> tbDatasource;
	public static volatile ListAttribute<Analysi, AnalysisColumn> tbAnalysisColumns;
	public static volatile ListAttribute<Analysi, BiAnalysi> tbBiAnalysis;
	public static volatile ListAttribute<Analysi, CmbViewer> tbCmbViewers;
	public static volatile ListAttribute<Analysi, CsvData> tbCsvData;
	public static volatile ListAttribute<Analysi, DbAnalysi> tbDbAnalysis;
	public static volatile ListAttribute<Analysi, EndecaAnalysi> tbEndecaAnalysis;
	public static volatile ListAttribute<Analysi, HdfsAnalysi> tbHdfsAnalysis;
	public static volatile ListAttribute<Analysi, TbWebserviceAnalysi> tbWebserviceAnalysis;
	public static volatile ListAttribute<Analysi, TxtSolrAnalysi> txtSolrAnalysis;
}
