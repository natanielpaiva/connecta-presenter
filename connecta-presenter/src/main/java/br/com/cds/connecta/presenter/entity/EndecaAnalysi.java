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
 * The persistent class for the TB_ENDECA_ANALYSIS database table.
 * 
 */
@Entity
@Table(name="TB_ENDECA_ANALYSIS")
@NamedQuery(name="EndecaAnalysi.findAll", query="SELECT t FROM EndecaAnalysi t")
public class EndecaAnalysi extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ENDECA_ANALYSIS_PKENDECAANALYSIS_GENERATOR", sequenceName="TB_ENDECA_ANALYSIS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ENDECA_ANALYSIS_PKENDECAANALYSIS_GENERATOR")
	@Column(name="PK_ENDECA_ANALYSIS")
	private Long id;

	@Column(name="TXT_DATA_DOMAIN")
	private String txtDataDomain;

	@Column(name="TXT_QUERY")
	private String txtQuery;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS")
	private Analysi tbAnalysi;

	public EndecaAnalysi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxtDataDomain() {
		return this.txtDataDomain;
	}

	public void setTxtDataDomain(String txtDataDomain) {
		this.txtDataDomain = txtDataDomain;
	}

	public String getTxtQuery() {
		return this.txtQuery;
	}

	public void setTxtQuery(String txtQuery) {
		this.txtQuery = txtQuery;
	}

	public Analysi getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysi tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

}