package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * The persistent class for the TB_BI_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_BI_ANALYSIS")
@SQLDelete(sql = "update TB_BI_ANALYSIS set IS_ACTIVE = 0 where PK_ANALYSIS = ?")
@Where(clause = "IS_ACTIVE = 1")
public class BIAnalysis extends Analysis {

	private static final long serialVersionUID = 1L;

	@Column(name = "TXT_ANALYSIS")
	private String analysis;

	@Column(name = "TXT_CATALOG")
	private String catalog;

	@Column(name = "TXT_FIELDS")
	private String fields;

	@Column(name = "TXT_FROM")
	private String from;

	@Column(name = "TXT_WHERE")
	private String where;

	@Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
	private boolean isActive = true;

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
