package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.236-0200")
@StaticMetamodel(AnalysisViewer.class)
public class TbAnalysisViewer_ {
	public static volatile SingularAttribute<AnalysisViewer, Long> pkAnalysisViewer;
	public static volatile SingularAttribute<AnalysisViewer, String> nmLabel;
	public static volatile SingularAttribute<AnalysisViewer, BigDecimal> nuIntrvlAtlzco;
	public static volatile SingularAttribute<AnalysisViewer, BigDecimal> nuMaxLinhas;
	public static volatile ListAttribute<AnalysisViewer, AnalysisVw> taAnalysisVwColumns;
	public static volatile ListAttribute<AnalysisViewer, ConfigViewer_ta> taConfigViewers;
	public static volatile ListAttribute<AnalysisViewer, CstmPrmVw> taCstmPrmVws;
	public static volatile SingularAttribute<AnalysisViewer, TbViewer> tbViewer;
}
