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
 * The persistent class for the TB_CSV_DATA database table.
 * 
 */
@Entity
@Table(name="TB_CSV_DATA")
@NamedQuery(name="CsvData.findAll", query="SELECT t FROM CsvData t")
public class CsvData extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CSV_DATA_PKCSVDATA_GENERATOR", sequenceName="TB_CSV_DATA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CSV_DATA_PKCSVDATA_GENERATOR")
	@Column(name="PK_CSV_DATA")
	private Long id;

	@Column(name="ID_ROW")
	private BigDecimal idRow;

	@Column(name="TXT_DATA_ROW")
	private String txtDataRow;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS")
	private Analysis tbAnalysi;

	public CsvData() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getIdRow() {
		return this.idRow;
	}

	public void setIdRow(BigDecimal idRow) {
		this.idRow = idRow;
	}

	public String getTxtDataRow() {
		return this.txtDataRow;
	}

	public void setTxtDataRow(String txtDataRow) {
		this.txtDataRow = txtDataRow;
	}

	public Analysis getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysis tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

}