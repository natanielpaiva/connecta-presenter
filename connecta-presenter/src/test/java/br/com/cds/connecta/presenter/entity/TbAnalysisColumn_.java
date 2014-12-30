package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.232-0200")
@StaticMetamodel(AnalysisColumn.class)
public class TbAnalysisColumn_ {
	public static volatile SingularAttribute<AnalysisColumn, Long> pkAnalysisColumns;
	public static volatile SingularAttribute<AnalysisColumn, String> fmlColumn;
	public static volatile SingularAttribute<AnalysisColumn, String> lbColumn;
	public static volatile SingularAttribute<AnalysisColumn, String> nmColumn;
	public static volatile SingularAttribute<AnalysisColumn, BigDecimal> tpColumn;
	public static volatile ListAttribute<AnalysisColumn, AnalysisVw> taAnalysisVwColumns;
	public static volatile SingularAttribute<AnalysisColumn, Analysi> tbAnalysi;
	public static volatile SingularAttribute<AnalysisColumn, CombinedAnalysi> tbCombinedAnalysi;
}
