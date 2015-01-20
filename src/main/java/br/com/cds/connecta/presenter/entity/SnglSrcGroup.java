package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;


/**
 * The persistent class for the TA_SNGL_SRC_GROUP database table.
 * 
 */
@Entity
@Table(name="TA_SNGL_SRC_GROUP")
@NamedQuery(name="SnglSrcGroup.findAll", query="SELECT t FROM SnglSrcGroup t")
public class SnglSrcGroup extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_SNGL_SRC_GROUP_PKSNGLSRCGROUP_GENERATOR", sequenceName="TA_SNGL_SRC_GROUP_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_SNGL_SRC_GROUP_PKSNGLSRCGROUP_GENERATOR")
	@Column(name="PK_SNGL_SRC_GROUP")
	private Long id;

	@Column(name="NU_ORDER")
	private BigDecimal nuOrder;

	//bi-directional many-to-one association to TbGroup
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_GROUP")
	private Group tbGroup;

	//bi-directional many-to-one association to TbSingleSource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_SINGLE_SOURCE")
	private SingleSource tbSingleSource;

	public SnglSrcGroup() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getNuOrder() {
		return this.nuOrder;
	}

	public void setNuOrder(BigDecimal nuOrder) {
		this.nuOrder = nuOrder;
	}

	public Group getTbGroup() {
		return this.tbGroup;
	}

	public void setTbGroup(Group tbGroup) {
		this.tbGroup = tbGroup;
	}

	public SingleSource getTbSingleSource() {
		return this.tbSingleSource;
	}

	public void setTbSingleSource(SingleSource tbSingleSource) {
		this.tbSingleSource = tbSingleSource;
	}

}