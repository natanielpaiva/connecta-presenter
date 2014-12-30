package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.217-0200")
@StaticMetamodel(AnalysisVw.class)
public class TaAnalysisVwColumn_ {
	public static volatile SingularAttribute<AnalysisVw, Long> pkAnalysisVwColumns;
	public static volatile SingularAttribute<AnalysisVw, String> tpColumn;
	public static volatile SingularAttribute<AnalysisVw, String> tpOrder;
	public static volatile SingularAttribute<AnalysisVw, String> tpType;
	public static volatile SingularAttribute<AnalysisVw, String> txtMaskFormat;
	public static volatile SingularAttribute<AnalysisVw, AnalysisViewer> tbAnalysisViewer;
	public static volatile SingularAttribute<AnalysisVw, AnalysisColumn> tbAnalysisColumn;
	public static volatile ListAttribute<AnalysisVw, ConfigColumn> taConfigColumns;
}
