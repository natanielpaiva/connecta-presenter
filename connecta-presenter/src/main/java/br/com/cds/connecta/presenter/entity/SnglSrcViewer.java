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
 * The persistent class for the TB_SNGL_SRC_VIEWER database table.
 * 
 */
@Entity
@Table(name="TB_SNGL_SRC_VIEWER")
@NamedQuery(name="SnglSrcViewer.findAll", query="SELECT t FROM SnglSrcViewer t")
public class SnglSrcViewer extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_SNGL_SRC_VIEWER_PKSNGLSRCVIEWER_GENERATOR", sequenceName="TB_SNGL_SRC_VIEWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_SNGL_SRC_VIEWER_PKSNGLSRCVIEWER_GENERATOR")
	@Column(name="PK_SNGL_SRC_VIEWER")
	private Long id;

	@Column(name="TP_ORGANIZATION")
	private String tpOrganization;

	@Column(name="TP_VIEWER")
	private String tpViewer;

	//bi-directional many-to-one association to TbViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_VIEWER")
	private TbViewer tbViewer;

	public SnglSrcViewer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTpOrganization() {
		return this.tpOrganization;
	}

	public void setTpOrganization(String tpOrganization) {
		this.tpOrganization = tpOrganization;
	}

	public String getTpViewer() {
		return this.tpViewer;
	}

	public void setTpViewer(String tpViewer) {
		this.tpViewer = tpViewer;
	}
	
	public TbViewer getTbViewer() {
		return this.tbViewer;
	}

	public void setTbViewer(TbViewer tbViewer) {
		this.tbViewer = tbViewer;
	}

}