package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.225-0200")
@StaticMetamodel(CstmPrmVw.class)
public class TaCstmPrmVw_ {
	public static volatile SingularAttribute<CstmPrmVw, Long> pkCstmPrmVw;
	public static volatile SingularAttribute<CstmPrmVw, String> nmAttribute;
	public static volatile SingularAttribute<CstmPrmVw, String> tpAttribute;
	public static volatile SingularAttribute<CstmPrmVw, String> txtMaskAttribute;
	public static volatile SingularAttribute<CstmPrmVw, String> txtValue;
	public static volatile SingularAttribute<CstmPrmVw, AnalysisViewer> tbAnalysisViewer;
	public static volatile ListAttribute<CstmPrmVw, ConfigCstmPrm> tbConfigCstmPrms;
}
