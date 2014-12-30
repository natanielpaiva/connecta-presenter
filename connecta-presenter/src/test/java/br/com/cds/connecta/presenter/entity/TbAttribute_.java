package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.237-0200")
@StaticMetamodel(Attribute.class)
public class TbAttribute_ {
	public static volatile SingularAttribute<Attribute, Long> pkAttribute;
	public static volatile SingularAttribute<Attribute, String> dsAttribute;
	public static volatile SingularAttribute<Attribute, String> nmAttribute;
	public static volatile SingularAttribute<Attribute, AttrAnalysi> taAttrAnalysi;
	public static volatile ListAttribute<Attribute, AttrGroup> taAttrGroups;
	public static volatile ListAttribute<Attribute, AttrSnglSrc> taAttrSnglSrcs;
}
