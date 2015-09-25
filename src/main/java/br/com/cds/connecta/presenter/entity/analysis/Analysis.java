package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.presenter.entity.datasource.Datasource;
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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;

/**
 * The persistent class for the TB_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_ANALYSIS")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Analysis.findAll", query = "SELECT t FROM Analysis t"),
    @NamedQuery(name = "Analysis.findById", query = "SELECT t FROM Analysis t "
            + "INNER JOIN FETCH t.analysisColumns a "
            + "INNER JOIN FETCH t.datasource d WHERE a.id = :id "),
     @NamedQuery(name = "Analysis.find", query = "SELECT a FROM Analysis a"
           + " INNER JOIN FETCH a.datasource d"
           + " INNER JOIN FETCH a.analysisColumns c WHERE a.id = :id ")
})
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(name = "DATABASE", value = DatabaseAnalysis.class),
    @JsonSubTypes.Type(name = "ENDECA", value = EndecaAnalysis.class),
    @JsonSubTypes.Type(name = "HDFS", value = HdfsAnalysis.class),
    @JsonSubTypes.Type(name = "BI", value = BIAnalysis.class),
    @JsonSubTypes.Type(name = "SOLR", value = SolrAnalysis.class),
    @JsonSubTypes.Type(name = "WEBSERVICE", value = WebserviceAnalysis.class)
})
public class Analysis extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_ANALYSIS_SEQ", sequenceName = "TB_ANALYSIS_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_ANALYSIS_SEQ")
    @Column(name = "PK_ANALYSIS")
    private Long id;

    @Column(name = "DS_ANALYSIS")
    private String description;

    @Column(name = "NM_ANALYSIS")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_DATASOURCE")
    private Datasource datasource;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ANALYSIS", nullable = false)
    private List<AnalysisColumn> analysisColumns;

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
}
