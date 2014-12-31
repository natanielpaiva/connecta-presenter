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
 * The persistent class for the TB_VIEWER database table.
 * 
 */
@Entity
@Table(name="TB_VIEWER")
@NamedQuery(name="Viewer.findAll", query="SELECT t FROM Viewer t")
public class Viewer extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_VIEWER_PKVIEWER_GENERATOR", sequenceName="TB_VIEWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_VIEWER_PKVIEWER_GENERATOR")
	@Column(name="PK_VIEWER")
	private Long id;

	@Column(name="DS_VIEWER")
	private String dsViewer;

	@Column(name="NM_VIEWER")
	private String nmViewer;

	//bi-directional many-to-one association to TbLayerViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_VIEWER")
	private LayerViewer_ta tbLayerViewer;

	public Viewer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsViewer() {
		return this.dsViewer;
	}

	public void setDsViewer(String dsViewer) {
		this.dsViewer = dsViewer;
	}

	public String getNmViewer() {
		return this.nmViewer;
	}

	public void setNmViewer(String nmViewer) {
		this.nmViewer = nmViewer;
	}

	public LayerViewer_ta getTbLayerViewer() {
		return this.tbLayerViewer;
	}

	public void setTbLayerViewer(LayerViewer_ta tbLayerViewer) {
		this.tbLayerViewer = tbLayerViewer;
	}
}