package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * The persistent class for the TB_ENDECA_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_ENDECA_ANALYSIS")
@SQLDelete(sql = "update TB_ENDECA_ANALYSIS set IS_ACTIVE = 0 where PK_ANALYSIS = ?")
@Where(clause = "IS_ACTIVE = 1")
public class EndecaAnalysis extends Analysis {

	private static final long serialVersionUID = 1L;

	@Column(name = "TXT_DATA_DOMAIN")
	private String dataDomain;

	@Column(name = "TXT_QUERY")
	private String query;

	@Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
	private Boolean isActive = true;

	public String getDataDomain() {
		return dataDomain;
	}

	public void setDataDomain(String dataDomain) {
		this.dataDomain = dataDomain;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
