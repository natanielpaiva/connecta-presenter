package br.com.cds.connecta.presenter.entity;

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
 * The persistent class for the TA_SNGL_SRC_VW_GROUP database table.
 * 
 */
@Entity
@Table(name="TA_SNGL_SRC_VW_GROUP")
@NamedQuery(name="SnglSrcVwGroup.findAll", query="SELECT t FROM SnglSrcVwGroup t")
public class SnglSrcVwGroup  extends AbstractBaseEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_SNGL_SRC_VW_GROUP_PKSNGLSRCVWGROUP_GENERATOR", sequenceName="TA_SNGL_SRC_VW_GROUP_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_SNGL_SRC_VW_GROUP_PKSNGLSRCVWGROUP_GENERATOR")
	@Column(name="PK_SNGL_SRC_VW_GROUP")
	private Long id;

	//bi-directional many-to-one association to TbGroup
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_GROUP")
	private Group tbGroup;

	//bi-directional many-to-one association to TbSnglSrcViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_SNGL_SRC_VIEWER")
	private SnglSrcViewer tbSnglSrcViewer;

	public SnglSrcVwGroup() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Group getTbGroup() {
		return this.tbGroup;
	}

	public void setTbGroup(Group tbGroup) {
		this.tbGroup = tbGroup;
	}

	public SnglSrcViewer getTbSnglSrcViewer() {
		return this.tbSnglSrcViewer;
	}

	public void setTbSnglSrcViewer(SnglSrcViewer tbSnglSrcViewer) {
		this.tbSnglSrcViewer = tbSnglSrcViewer;
	}

}