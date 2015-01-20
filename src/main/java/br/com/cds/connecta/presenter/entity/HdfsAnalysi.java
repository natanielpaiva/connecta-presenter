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
 * The persistent class for the TB_HDFS_ANALYSIS database table.
 * 
 */
@Entity
@Table(name="TB_HDFS_ANALYSIS")
@NamedQuery(name="HdfsAnalysi.findAll", query="SELECT t FROM HdfsAnalysi t")
public class HdfsAnalysi extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HDFS_ANALYSIS_PKHDFSANALYSIS_GENERATOR", sequenceName="TB_HDFS_ANALYSIS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HDFS_ANALYSIS_PKHDFSANALYSIS_GENERATOR")
	@Column(name="PK_HDFS_ANALYSIS")
	private Long id;

	@Column(name="TXT_HIVEQL")
	private String txtHiveql;

	@Column(name="TXT_MR_CLASS")
	private String txtMrClass;

	@Column(name="TXT_MR_METHOD")
	private String txtMrMethod;

	@Column(name="TXT_PIG_QUERY")
	private String txtPigQuery;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS")
	private Analysis tbAnalysi;

	public HdfsAnalysi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxtHiveql() {
		return this.txtHiveql;
	}

	public void setTxtHiveql(String txtHiveql) {
		this.txtHiveql = txtHiveql;
	}

	public String getTxtMrClass() {
		return this.txtMrClass;
	}

	public void setTxtMrClass(String txtMrClass) {
		this.txtMrClass = txtMrClass;
	}

	public String getTxtMrMethod() {
		return this.txtMrMethod;
	}

	public void setTxtMrMethod(String txtMrMethod) {
		this.txtMrMethod = txtMrMethod;
	}

	public String getTxtPigQuery() {
		return this.txtPigQuery;
	}

	public void setTxtPigQuery(String txtPigQuery) {
		this.txtPigQuery = txtPigQuery;
	}

	public Analysis getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysis tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

}