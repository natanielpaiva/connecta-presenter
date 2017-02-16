package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import javax.persistence.Basic;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;

/**
 * The persistent class for the TB_DATABASE_DS database table.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TB_DATABASE_DS")
@NamedQueries({
		@NamedQuery(name = "DatabaseDatasource.findById", query = "SELECT t FROM DatabaseDatasource t  WHERE t.id = :id "),
		@NamedQuery(name = "DatabaseDatasource.findAll", query = "SELECT t FROM DatabaseDatasource t") })

@SQLDelete(sql = "update TB_DATABASE_DS set IS_ACTIVE = 0 where PK_DATASOURCE = ?")
@Where(clause = "IS_ACTIVE = 1")
public class DatabaseDatasource extends Datasource {

	@Column(name = "CD_DRIVER")
	@Enumerated(EnumType.STRING)
	private DatabaseDatasourceDriverEnum driver;

	@Column(name = "TXT_SERVER")
	private String server;

	@Column(name = "TXT_PORT")
	private Integer port;

	@Column(name = "TXT_SID")
	private String sid;

	@Basic(optional = true)
	@Column(name = "TXT_SCHEMA")
	private String schema;

	@Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
	private Boolean isActive = true;

	public DatabaseDatasourceDriverEnum getDriver() {
		return driver;
	}

	public void setDriver(DatabaseDatasourceDriverEnum driver) {
		this.driver = driver;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}