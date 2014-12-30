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
 * The persistent class for the TA_SNGL_SRC_VW_SSRC database table.
 * 
 */
@Entity
@Table(name="TA_SNGL_SRC_VW_SSRC")
@NamedQuery(name="SnglSrcVwSsrc.findAll", query="SELECT t FROM SnglSrcVwSsrc t")
public class SnglSrcVwSsrc extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_SNGL_SRC_VW_SSRC_PKSNGLSRCVWSSRC_GENERATOR", sequenceName="TA_SNGL_SRC_VW_SSRC_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_SNGL_SRC_VW_SSRC_PKSNGLSRCVWSSRC_GENERATOR")
	@Column(name="PK_SNGL_SRC_VW_SSRC")
	private Long id;

	//bi-directional many-to-one association to TbSingleSource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_SINGLE_SOURCE")
	private SingleSource tbSingleSource;

	//bi-directional many-to-one association to TbSnglSrcViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_SNGL_SRC_VIEWER")
	private SnglSrcViewer tbSnglSrcViewer;

	public SnglSrcVwSsrc() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SingleSource getTbSingleSource() {
		return this.tbSingleSource;
	}

	public void setTbSingleSource(SingleSource tbSingleSource) {
		this.tbSingleSource = tbSingleSource;
	}

	public SnglSrcViewer getTbSnglSrcViewer() {
		return this.tbSnglSrcViewer;
	}

	public void setTbSnglSrcViewer(SnglSrcViewer tbSnglSrcViewer) {
		this.tbSnglSrcViewer = tbSnglSrcViewer;
	}

}