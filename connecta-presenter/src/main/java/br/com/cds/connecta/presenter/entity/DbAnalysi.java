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
 * The persistent class for the TB_DB_ANALYSIS database table.
 * 
 */
@Entity
@Table(name="TB_DB_ANALYSIS")
@NamedQuery(name="DbAnalysi.findAll", query="SELECT t FROM DbAnalysi t")
public class DbAnalysi extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DB_ANALYSIS_PKDBANALYSIS_GENERATOR", sequenceName="TB_DB_ANALYSIS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DB_ANALYSIS_PKDBANALYSIS_GENERATOR")
	@Column(name="PK_DB_ANALYSIS")
	private Long id;

	@Column(name="TXT_FIELDS")
	private String txtFields;

	@Column(name="TXT_FROM")
	private String txtFrom;

	@Column(name="TXT_WHERE")
	private String txtWhere;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ANALYSIS")
	private Analysi tbAnalysi;

	public DbAnalysi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxtFields() {
		return this.txtFields;
	}

	public void setTxtFields(String txtFields) {
		this.txtFields = txtFields;
	}

	public String getTxtFrom() {
		return this.txtFrom;
	}

	public void setTxtFrom(String txtFrom) {
		this.txtFrom = txtFrom;
	}

	public String getTxtWhere() {
		return this.txtWhere;
	}

	public void setTxtWhere(String txtWhere) {
		this.txtWhere = txtWhere;
	}

	public Analysi getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysi tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

}