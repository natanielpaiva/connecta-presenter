package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;


/**
 * The persistent class for the TB_DATASOURCE database table.
 * 
 */
@Entity
@Table(name="TB_DATASOURCE")
@NamedQuery(name="Datasource.findAll", query="SELECT t FROM Datasource t")
public class Datasource extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DATASOURCE_PKDATASOURCE_GENERATOR", sequenceName="TB_DATASOURCE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DATASOURCE_PKDATASOURCE_GENERATOR")
	@Column(name="PK_DATASOURCE")
	private Long id;

	@Column(name="DS_DATASOURCE")
	private String dsDatasource;

	@Column(name="NM_DATASOURCE")
	private String nmDatasource;

	@Column(name="TP_DATASOURCE")
	private String tpDatasource;

	@Column(name="TXT_SENHA")
	private String txtSenha;

	@Column(name="TXT_USUARIO")
	private String txtUsuario;

	public Datasource() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsDatasource() {
		return this.dsDatasource;
	}

	public void setDsDatasource(String dsDatasource) {
		this.dsDatasource = dsDatasource;
	}

	public String getNmDatasource() {
		return this.nmDatasource;
	}

	public void setNmDatasource(String nmDatasource) {
		this.nmDatasource = nmDatasource;
	}

	public String getTpDatasource() {
		return this.tpDatasource;
	}

	public void setTpDatasource(String tpDatasource) {
		this.tpDatasource = tpDatasource;
	}

	public String getTxtSenha() {
		return this.txtSenha;
	}

	public void setTxtSenha(String txtSenha) {
		this.txtSenha = txtSenha;
	}

	public String getTxtUsuario() {
		return this.txtUsuario;
	}

	public void setTxtUsuario(String txtUsuario) {
		this.txtUsuario = txtUsuario;
	}
}