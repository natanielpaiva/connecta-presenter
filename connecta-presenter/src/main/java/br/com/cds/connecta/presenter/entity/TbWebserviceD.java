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
 * The persistent class for the TB_WEBSERVICE_DS database table.
 * 
 */
@Entity
@Table(name="TB_WEBSERVICE_DS")
@NamedQuery(name="TbWebserviceD.findAll", query="SELECT t FROM TbWebserviceD t")
public class TbWebserviceD extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_WEBSERVICE_DS_PKWEBSERVICEDS_GENERATOR", sequenceName="TB_WEBSERVICE_DS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_WEBSERVICE_DS_PKWEBSERVICEDS_GENERATOR")
	@Column(name="PK_WEBSERVICE_DS")
	private Long id;

	@Column(name="TXT_ADDRESS")
	private String txtAddress;

	@Column(name="TXT_PATH")
	private String txtPath;

	//bi-directional many-to-one association to TbDatasource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_DATASOURCE")
	private Datasource tbDatasource;

	public TbWebserviceD() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxtAddress() {
		return this.txtAddress;
	}

	public void setTxtAddress(String txtAddress) {
		this.txtAddress = txtAddress;
	}

	public String getTxtPath() {
		return this.txtPath;
	}

	public void setTxtPath(String txtPath) {
		this.txtPath = txtPath;
	}

	public Datasource getTbDatasource() {
		return this.tbDatasource;
	}

	public void setTbDatasource(Datasource tbDatasource) {
		this.tbDatasource = tbDatasource;
	}

}