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
 * The persistent class for the TB_WEBSERVICE_ANALYSIS database table.
 * 
 */
@Entity
@Table(name="TB_WEBSERVICE_ANALYSIS")
@NamedQuery(name="WebserviceAnalysi.findAll", query="SELECT t FROM WebserviceAnalysi t")
public class WebserviceAnalysi extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_WEBSERVICE_ANALYSIS_PKWSANALYSIS_GENERATOR", sequenceName="TB_WEBSERVICE_ANALYSIS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_WEBSERVICE_ANALYSIS_PKWSANALYSIS_GENERATOR")
	@Column(name="PK_WS_ANALYSIS")
	private Long id;

	@Column(name="TP_WS")
	private String tpWs;

	@Column(name="TXT_METHOD")
	private String txtMethod;

	@Column(name="TXT_PARAMS")
	private String txtParams;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS")
	private Analysi tbAnalysi;

	public WebserviceAnalysi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTpWs() {
		return this.tpWs;
	}

	public void setTpWs(String tpWs) {
		this.tpWs = tpWs;
	}

	public String getTxtMethod() {
		return this.txtMethod;
	}

	public void setTxtMethod(String txtMethod) {
		this.txtMethod = txtMethod;
	}

	public String getTxtParams() {
		return this.txtParams;
	}

	public void setTxtParams(String txtParams) {
		this.txtParams = txtParams;
	}

	public Analysi getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysi tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

}