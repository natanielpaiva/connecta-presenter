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
 * The persistent class for the TB_ENDECA_DS database table.
 * 
 */
@Entity
@Table(name="TB_ENDECA_DS")
@NamedQuery(name="EndecaDS.findAll", query="SELECT t FROM EndecaDS t")
public class EndecaDS extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ENDECA_DS_PKENDECADS_GENERATOR", sequenceName="TB_ENDECA_DS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ENDECA_DS_PKENDECADS_GENERATOR")
	@Column(name="PK_ENDECA_DS")
	private Long id;

	@Column(name="TXT_ADDRESS")
	private String txtAddress;

	//bi-directional many-to-one association to TbDatasource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_DATASOURCE")
	private Datasource tbDatasource;

	public EndecaDS() {
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

	public Datasource getTbDatasource() {
		return this.tbDatasource;
	}

	public void setTbDatasource(Datasource tbDatasource) {
		this.tbDatasource = tbDatasource;
	}

}