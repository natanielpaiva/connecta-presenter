package br.com.cds.connecta.presenter.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.263-0200")
@StaticMetamodel(Group.class)
public class TbGroup_ {
	public static volatile SingularAttribute<Group, Long> pkGroup;
	public static volatile SingularAttribute<Group, String> dsGroup;
	public static volatile SingularAttribute<Group, String> nmGroup;
	public static volatile SingularAttribute<Group, String> tpGroup;
	public static volatile SingularAttribute<Group, String> txtQuery;
	public static volatile ListAttribute<Group, AttrGroup> taAttrGroups;
	public static volatile ListAttribute<Group, SnglSrcGroup> taSnglSrcGroups;
	public static volatile ListAttribute<Group, SnglSrcVwGroup> taSnglSrcVwGroups;
}
