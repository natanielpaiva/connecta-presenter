package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.245-0200")
@StaticMetamodel(ConfigViewer.class)
public class TbConfigViewer_ {
	public static volatile SingularAttribute<ConfigViewer, Long> pkConfigViewer;
	public static volatile SingularAttribute<ConfigViewer, String> fmlAttribute;
	public static volatile SingularAttribute<ConfigViewer, String> nmAttribute;
	public static volatile ListAttribute<ConfigViewer, ConfigColumn> taConfigColumns;
	public static volatile ListAttribute<ConfigViewer, ConfigViewer_ta> taConfigViewers;
	public static volatile ListAttribute<ConfigViewer, ConfigCstmPrm> tbConfigCstmPrms;
}
