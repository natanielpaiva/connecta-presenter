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
 * The persistent class for the TB_CONFIG_CSTM_PRM database table.
 * 
 */
@Entity
@Table(name="TB_CONFIG_CSTM_PRM")
@NamedQuery(name="ConfigCstmPrm.findAll", query="SELECT t FROM ConfigCstmPrm t")
public class ConfigCstmPrm extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CONFIG_CSTM_PRM_PKCONFIGCSTMPRM_GENERATOR", sequenceName="TB_CONFIG_CSTM_PRM_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONFIG_CSTM_PRM_PKCONFIGCSTMPRM_GENERATOR")
	@Column(name="PK_CONFIG_CSTM_PRM")
	private Long id;

	@Column(name="TXT_VALUE")
	private String txtValue;

	//bi-directional many-to-one association to TaCstmPrmVw
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_CSTM_PRM_VW")
	private CstmPrmVw taCstmPrmVw;

	//bi-directional many-to-one association to TbConfigViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_CONFIG_VIEWER")
	private ConfigViewer tbConfigViewer;

	public ConfigCstmPrm() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxtValue() {
		return this.txtValue;
	}

	public void setTxtValue(String txtValue) {
		this.txtValue = txtValue;
	}

	public CstmPrmVw getTaCstmPrmVw() {
		return this.taCstmPrmVw;
	}

	public void setTaCstmPrmVw(CstmPrmVw taCstmPrmVw) {
		this.taCstmPrmVw = taCstmPrmVw;
	}

	public ConfigViewer getTbConfigViewer() {
		return this.tbConfigViewer;
	}

	public void setTbConfigViewer(ConfigViewer tbConfigViewer) {
		this.tbConfigViewer = tbConfigViewer;
	}

}