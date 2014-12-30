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
 * The persistent class for the TB_URL_WEB database table.
 * 
 */
@Entity
@Table(name="TB_URL_WEB")
@NamedQuery(name="UrlWeb.findAll", query="SELECT t FROM UrlWeb t")
public class UrlWeb extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_URL_WEB_PKURLWEB_GENERATOR", sequenceName="TB_URL_WEB_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_URL_WEB_PKURLWEB_GENERATOR")
	@Column(name="PK_URL_WEB")
	private Long id;

	@Column(name="TP_URL_WEB")
	private String tpUrlWeb;

	@Column(name="TXT_PASSWORD")
	private String txtPassword;

	@Column(name="TXT_URL")
	private String txtUrl;

	@Column(name="TXT_USER")
	private String txtUser;

	//bi-directional many-to-one association to TbSingleSource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_SINGLE_SOURCE")
	private SingleSource tbSingleSource;

	public UrlWeb() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTpUrlWeb() {
		return this.tpUrlWeb;
	}

	public void setTpUrlWeb(String tpUrlWeb) {
		this.tpUrlWeb = tpUrlWeb;
	}

	public String getTxtPassword() {
		return this.txtPassword;
	}

	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}

	public String getTxtUrl() {
		return this.txtUrl;
	}

	public void setTxtUrl(String txtUrl) {
		this.txtUrl = txtUrl;
	}

	public String getTxtUser() {
		return this.txtUser;
	}

	public void setTxtUser(String txtUser) {
		this.txtUser = txtUser;
	}

	public SingleSource getTbSingleSource() {
		return this.tbSingleSource;
	}

	public void setTbSingleSource(SingleSource tbSingleSource) {
		this.tbSingleSource = tbSingleSource;
	}

}