package br.com.cds.connecta.presenter.entity.analysis;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.domain.DatasourceTypeEnum;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;

/**
 * The persistent class for the TB_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_ANALYSIS")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ 
		@JsonSubTypes.Type(name = "DATABASE", value = DatabaseAnalysis.class),
		@JsonSubTypes.Type(name = "ENDECA", value = EndecaAnalysis.class),
		@JsonSubTypes.Type(name = "HDFS", value = HdfsAnalysis.class),
		@JsonSubTypes.Type(name = "BI", value = BIAnalysis.class),
		@JsonSubTypes.Type(name = "SOLR", value = SolrAnalysis.class),
		@JsonSubTypes.Type(name = "WEBSERVICE", value = WebserviceAnalysis.class),
		@JsonSubTypes.Type(name = "CSV", value = CsvAnalysis.class),
		@JsonSubTypes.Type(name = "COMBINED", value = CombinedAnalysis.class),
		@JsonSubTypes.Type(name = "REST", value = RestAnalysis.class),
                @JsonSubTypes.Type(name = "WSO2", value = Wso2Analysis.class)})

@SQLDelete(sql = "update TB_ANALYSIS set IS_ACTIVE = 0 where PK_ANALYSIS = ?")
@Where(clause = "IS_ACTIVE = 1")
public class Analysis extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ANALYSIS")
	private Long id;

	@Column(name = "DS_ANALYSIS")
	private String description;

	@Column(name = "NM_ANALYSIS")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_DATASOURCE")
	private Datasource datasource;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ANALYSIS", nullable = false)
	private Set<AnalysisColumn> analysisColumns;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ANALYSIS")
	private Set<AnalysisAttribute> analysisAttributes;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_LEFT_ANALYSIS")
	private List<AnalysisRelation> analysisRelations;

	@Column(name = "FL_DRILL")
	private boolean hasDrill;

	@Column(name = "FL_CACHED")
	private boolean isCached;

	/**
	 * FIXME colocar o tipo certo, da Análise
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "TP_ANALYSIS")
	private DatasourceTypeEnum type;

	@Column(name = "NM_DOMAIN")
	private String domain;

	@Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
	private Boolean isActive = true;

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Datasource getDatasource() {
		return datasource;
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}

	public Set<AnalysisColumn> getAnalysisColumns() {
		return analysisColumns;
	}

	public void setAnalysisColumns(Set<AnalysisColumn> analysisColumns) {
		this.analysisColumns = analysisColumns;
	}

	public Set<AnalysisAttribute> getAnalysisAttributes() {
		return analysisAttributes;
	}

	public void setAnalysisAttributes(Set<AnalysisAttribute> analysisAttributes) {
		this.analysisAttributes = analysisAttributes;
	}

	public List<AnalysisRelation> getAnalysisRelations() {
		return analysisRelations;
	}

	public void setAnalysisRelations(List<AnalysisRelation> analysisRelations) {
		this.analysisRelations = analysisRelations;
	}

	public DatasourceTypeEnum getType() {
		return type;
	}

	public void setType(DatasourceTypeEnum type) {
		this.type = type;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public boolean getHasDrill() {
		return hasDrill;
	}

	public void setHasDrill(boolean hasDrill) {
		this.hasDrill = hasDrill;
	}

	public boolean isCached() {
		return isCached;
	}

	public void setCached(boolean isCached) {
		this.isCached = isCached;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
