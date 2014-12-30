package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.274-0200")
@StaticMetamodel(RelationCmbAnalysi.class)
public class TbRelationCmbAnalysi_ {
	public static volatile SingularAttribute<RelationCmbAnalysi, Long> pkRelationCmbAnalysis;
	public static volatile SingularAttribute<RelationCmbAnalysi, BigDecimal> idSource1;
	public static volatile SingularAttribute<RelationCmbAnalysi, BigDecimal> idSource2;
	public static volatile SingularAttribute<RelationCmbAnalysi, BigDecimal> nuOrder;
	public static volatile SingularAttribute<RelationCmbAnalysi, String> tpSource1;
	public static volatile SingularAttribute<RelationCmbAnalysi, String> tpSource2;
	public static volatile SingularAttribute<RelationCmbAnalysi, CombinedAnalysi> tbCombinedAnalysi;
	public static volatile ListAttribute<RelationCmbAnalysi, RltnCmbAnalysisJoin> tbRltnCmbAnalysisJoins;
}
