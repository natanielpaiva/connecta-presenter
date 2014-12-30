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
 * The persistent class for the TB_HDFS_DS database table.
 * 
 */
@Entity
@Table(name="TB_HDFS_DS")
@NamedQuery(name="HdfsD.findAll", query="SELECT t FROM HdfsD t")
public class HdfsD extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HDFS_DS_PKHDFSDS_GENERATOR", sequenceName="TB_HDFS_DS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HDFS_DS_PKHDFSDS_GENERATOR")
	@Column(name="PK_HDFS_DS")
	private Long id;

	@Column(name="TXT_PATH")
	private String txtPath;

	@Column(name="TXT_PORT")
	private BigDecimal txtPort;

	@Column(name="TXT_SERVER")
	private String txtServer;

	//bi-directional many-to-one association to TbDatasource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_DATASOURCE")
	private Datasource tbDatasource;

	public HdfsD() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxtPath() {
		return this.txtPath;
	}

	public void setTxtPath(String txtPath) {
		this.txtPath = txtPath;
	}

	public BigDecimal getTxtPort() {
		return this.txtPort;
	}

	public void setTxtPort(BigDecimal txtPort) {
		this.txtPort = txtPort;
	}

	public String getTxtServer() {
		return this.txtServer;
	}

	public void setTxtServer(String txtServer) {
		this.txtServer = txtServer;
	}

	public Datasource getTbDatasource() {
		return this.tbDatasource;
	}

	public void setTbDatasource(Datasource tbDatasource) {
		this.tbDatasource = tbDatasource;
	}

}