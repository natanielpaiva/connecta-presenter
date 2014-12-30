package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;


/**
 * The persistent class for the TB_ANALYSIS_SOURCE database table.
 * 
 */
@Entity
@Table(name="TB_ANALYSIS_SOURCE")
@NamedQuery(name="AnalysisSource.findAll", query="SELECT t FROM AnalysisSource t")
public class AnalysisSource extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ANALYSIS_SOURCE_IDANALYSISSOURCE_GENERATOR", sequenceName="TB_ANALYSIS_SOURCE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ANALYSIS_SOURCE_IDANALYSISSOURCE_GENERATOR")
	@Column(name="ID_ANALYSIS_SOURCE")
	private Long id;

	@Column(name="DS_URL_SOURCE")
	private String dsUrlSource;

	public AnalysisSource() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsUrlSource() {
		return this.dsUrlSource;
	}

	public void setDsUrlSource(String dsUrlSource) {
		this.dsUrlSource = dsUrlSource;
	}

}