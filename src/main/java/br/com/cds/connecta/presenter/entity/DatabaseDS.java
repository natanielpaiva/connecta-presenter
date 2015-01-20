package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;

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
 * The persistent class for the TB_DATABASE_DS database table.
 * 
 */
@Entity
@Table(name="TB_DATABASE_DS")
@NamedQuery(name="DatabaseDS.findAll", query="SELECT t FROM DatabaseDS t")
public class DatabaseDS extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DATABASE_DS_PKDATABASEDS_GENERATOR", sequenceName="TB_DATABASE_DS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DATABASE_DS_PKDATABASEDS_GENERATOR")
	@Column(name="PK_DATABASE_DS")
	private Long id;

	@Column(name="CD_DRIVER")
	private String cdDriver;

	@Column(name="TXT_PORT")
	private BigDecimal txtPort;

	@Column(name="TXT_SCHEMA")
	private String txtSchema;

	@Column(name="TXT_SERVER")
	private String txtServer;

	@Column(name="TXT_SID")
	private String txtSid;

	//bi-directional many-to-one association to TbDatasource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_DATASOURCE")
	private Datasource tbDatasource;

	public DatabaseDS() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCdDriver() {
		return this.cdDriver;
	}

	public void setCdDriver(String cdDriver) {
		this.cdDriver = cdDriver;
	}

	public BigDecimal getTxtPort() {
		return this.txtPort;
	}

	public void setTxtPort(BigDecimal txtPort) {
		this.txtPort = txtPort;
	}

	public String getTxtSchema() {
		return this.txtSchema;
	}

	public void setTxtSchema(String txtSchema) {
		this.txtSchema = txtSchema;
	}

	public String getTxtServer() {
		return this.txtServer;
	}

	public void setTxtServer(String txtServer) {
		this.txtServer = txtServer;
	}

	public String getTxtSid() {
		return this.txtSid;
	}

	public void setTxtSid(String txtSid) {
		this.txtSid = txtSid;
	}

	public Datasource getTbDatasource() {
		return this.tbDatasource;
	}

	public void setTbDatasource(Datasource tbDatasource) {
		this.tbDatasource = tbDatasource;
	}

}