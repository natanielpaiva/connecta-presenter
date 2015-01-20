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
 * The persistent class for the TA_CSTM_PRM_VW database table.
 * 
 */
@Entity
@Table(name="TA_CSTM_PRM_VW")
@NamedQuery(name="CstmPrmVw.findAll", query="SELECT t FROM CstmPrmVw t")
public class CstmPrmVw extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_CSTM_PRM_VW_PKCSTMPRMVW_GENERATOR", sequenceName="TA_CSTM_PRM_VW_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_CSTM_PRM_VW_PKCSTMPRMVW_GENERATOR")
	@Column(name="PK_CSTM_PRM_VW")
	private Long id;

	@Column(name="NM_ATTRIBUTE")
	private String nmAttribute;

	@Column(name="TP_ATTRIBUTE")
	private String tpAttribute;

	@Column(name="TXT_MASK_ATTRIBUTE")
	private String txtMaskAttribute;

	@Column(name="TXT_VALUE")
	private String txtValue;

	//bi-directional many-to-one association to TbAnalysisViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS_VIEWER")
	private AnalysisViewer tbAnalysisViewer;

	

	public CstmPrmVw() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmAttribute() {
		return this.nmAttribute;
	}

	public void setNmAttribute(String nmAttribute) {
		this.nmAttribute = nmAttribute;
	}

	public String getTpAttribute() {
		return this.tpAttribute;
	}

	public void setTpAttribute(String tpAttribute) {
		this.tpAttribute = tpAttribute;
	}

	public String getTxtMaskAttribute() {
		return this.txtMaskAttribute;
	}

	public void setTxtMaskAttribute(String txtMaskAttribute) {
		this.txtMaskAttribute = txtMaskAttribute;
	}

	public String getTxtValue() {
		return this.txtValue;
	}

	public void setTxtValue(String txtValue) {
		this.txtValue = txtValue;
	}

	public AnalysisViewer getTbAnalysisViewer() {
		return this.tbAnalysisViewer;
	}

	public void setTbAnalysisViewer(AnalysisViewer tbAnalysisViewer) {
		this.tbAnalysisViewer = tbAnalysisViewer;
	}
}