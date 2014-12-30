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
 * The persistent class for the TB_CMB_VIEWER database table.
 * 
 */
@Entity
@Table(name="TB_CMB_VIEWER")
@NamedQuery(name="CmbViewer.findAll", query="SELECT t FROM CmbViewer t")
public class CmbViewer extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CMB_VIEWER_PKCMBVIEWER_GENERATOR", sequenceName="TB_CMB_VIEWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CMB_VIEWER_PKCMBVIEWER_GENERATOR")
	@Column(name="PK_CMB_VIEWER")
	private Long id;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS")
	private Analysi tbAnalysi;

	//bi-directional many-to-one association to TbCombinedAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_COMBINED_ANALYSIS")
	private CombinedAnalysi tbCombinedAnalysi;

	//bi-directional many-to-one association to TbViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_VIEWER")
	private TbViewer tbViewer;

	public CmbViewer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Analysi getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysi tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

	public CombinedAnalysi getTbCombinedAnalysi() {
		return this.tbCombinedAnalysi;
	}

	public void setTbCombinedAnalysi(CombinedAnalysi tbCombinedAnalysi) {
		this.tbCombinedAnalysi = tbCombinedAnalysi;
	}

	public TbViewer getTbViewer() {
		return this.tbViewer;
	}

	public void setTbViewer(TbViewer tbViewer) {
		this.tbViewer = tbViewer;
	}

}