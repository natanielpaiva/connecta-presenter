package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.243-0200")
@StaticMetamodel(CombinedAnalysi.class)
public class TbCombinedAnalysi_ {
	public static volatile SingularAttribute<CombinedAnalysi, Long> pkCombinedAnalysis;
	public static volatile SingularAttribute<CombinedAnalysi, String> dsCombinedAnalysis;
	public static volatile SingularAttribute<CombinedAnalysi, String> nmCombinedAnalysis;
	public static volatile ListAttribute<CombinedAnalysi, AnalysisColumn> tbAnalysisColumns;
	public static volatile ListAttribute<CombinedAnalysi, CmbViewer> tbCmbViewers;
	public static volatile ListAttribute<CombinedAnalysi, RelationCmbAnalysi> tbRelationCmbAnalysis;
}
