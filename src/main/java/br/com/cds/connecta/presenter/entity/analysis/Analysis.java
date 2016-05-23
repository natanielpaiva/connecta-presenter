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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

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
@NamedQueries({
    @NamedQuery(name = "Analysis.findAll", query = "SELECT t FROM Analysis t"),
    @NamedQuery(name = "Analysis.findById", query = "SELECT t FROM Analysis t "
            + "INNER JOIN FETCH t.analysisColumns a "
            + "LEFT JOIN FETCH t.datasource d WHERE a.id = :id "),

    @NamedQuery(name = "Analysis.find", query = "SELECT a FROM Analysis a "
            + " LEFT JOIN FETCH a.analysisAttributes anAttr "
            + " LEFT JOIN FETCH anAttr.attribute attr "
            + " LEFT OUTER JOIN FETCH a.datasource d WHERE a.id = :id")

})
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(name = "DATABASE", value = DatabaseAnalysis.class),
    @JsonSubTypes.Type(name = "ENDECA", value = EndecaAnalysis.class),
    @JsonSubTypes.Type(name = "HDFS", value = HdfsAnalysis.class),
    @JsonSubTypes.Type(name = "BI", value = BIAnalysis.class),
    @JsonSubTypes.Type(name = "SOLR", value = SolrAnalysis.class),
    @JsonSubTypes.Type(name = "WEBSERVICE", value = WebserviceAnalysis.class),
    @JsonSubTypes.Type(name = "CSV", value = CsvAnalysis.class)
})
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "FK_ANALYSIS", nullable = false)
    private List<AnalysisColumn> analysisColumns;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ANALYSIS")
    private Set<AnalysisAttribute> analysisAttributes;

    @NotNull
    @Column(name = "FL_DRILL", nullable = false)
    private boolean hasDrill;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_ANALYSIS")
    private DatasourceTypeEnum type;

    @Column(name = "NM_DOMAIN")
    private String domain;

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

    public List<AnalysisColumn> getAnalysisColumns() {
        return analysisColumns;
    }

    public void setAnalysisColumns(List<AnalysisColumn> analysisColumns) {
        this.analysisColumns = analysisColumns;
    }

    public Set<AnalysisAttribute> getAnalysisAttributes() {
        return analysisAttributes;
    }

    public void setAnalysisAttributes(Set<AnalysisAttribute> analysisAttributes) {
        this.analysisAttributes = analysisAttributes;
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

}
