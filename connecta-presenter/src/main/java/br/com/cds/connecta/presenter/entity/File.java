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
 * The persistent class for the TB_FILE database table.
 * 
 */
@Entity
@Table(name="TB_FILE")
@NamedQuery(name="File.findAll", query="SELECT t FROM File t")
public class File extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_FILE_PKFILE_GENERATOR", sequenceName="TB_FILE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_FILE_PKFILE_GENERATOR")
	@Column(name="PK_FILE")
	private Long id;

	@Column(name="NM_FILE")
	private String nmFile;

	@Column(name="TP_FILE")
	private String tpFile;

	@Column(name="TXT_PASSWORD")
	private String txtPassword;

	@Column(name="TXT_USER")
	private String txtUser;

	@Column(name="URL_FILE")
	private String urlFile;

	//bi-directional many-to-one association to TbSingleSource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_SINGLE_SOURCE")
	private SingleSource tbSingleSource;

	public File() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmFile() {
		return this.nmFile;
	}

	public void setNmFile(String nmFile) {
		this.nmFile = nmFile;
	}

	public String getTpFile() {
		return this.tpFile;
	}

	public void setTpFile(String tpFile) {
		this.tpFile = tpFile;
	}

	public String getTxtPassword() {
		return this.txtPassword;
	}

	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}

	public String getTxtUser() {
		return this.txtUser;
	}

	public void setTxtUser(String txtUser) {
		this.txtUser = txtUser;
	}

	public String getUrlFile() {
		return this.urlFile;
	}

	public void setUrlFile(String urlFile) {
		this.urlFile = urlFile;
	}

	public SingleSource getTbSingleSource() {
		return this.tbSingleSource;
	}

	public void setTbSingleSource(SingleSource tbSingleSource) {
		this.tbSingleSource = tbSingleSource;
	}

}