package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.framework.amcharts.model.ChartConfiguration;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 * The persistent class for the TB_VIEWER database table.
 */
@Entity
@Indexed
@AnalyzerDef(
    name="viewerNameAnalyzer",
    tokenizer = @TokenizerDef(
        factory = StandardTokenizerFactory.class
    ),
    filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
            @Parameter(name = "language", value = "Portuguese")
        })
    }
)
@Table(name = "TB_VIEWER")
@NamedQueries({
    @NamedQuery(name = "Viewer.findAll", query = "SELECT t FROM Viewer t"),
    @NamedQuery(name = "Viewer.getById", query = "SELECT v FROM Viewer v WHERE v.id = :id ")
})
public class Viewer extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_VIEWER_SEQ", sequenceName = "TB_VIEWER_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_VIEWER_SEQ")
    @Column(name = "PK_VIEWER")
    private Long id;

    @Field
    @Analyzer(definition = "viewerNameAnalyzer")
    @Column(name = "NM_VIEWER")
    private String name;

    @Field
    @Analyzer(definition = "viewerNameAnalyzer")
    @Column(name = "DS_VIEWER")
    private String description;

    @Lob
    @Column(name = "CONFIGURATION")
    private ChartConfiguration configuration;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChartConfiguration getConfiguration() {
        return (ChartConfiguration) configuration;
    }

    public void setConfiguration(ChartConfiguration configuration) {
        this.configuration = configuration;
    }
}
