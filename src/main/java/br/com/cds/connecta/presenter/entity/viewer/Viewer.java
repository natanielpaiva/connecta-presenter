package br.com.cds.connecta.presenter.entity.viewer;

import br.com.cds.connecta.presenter.components.viewers.PresenterViewerConfiguration;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.framework.core.search.annotation.ConnectaViewer;
import br.com.cds.connecta.presenter.domain.ViewerTypeEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "TB_VIEWER")
@NamedQueries({
    @NamedQuery(name = "Viewer.findAll", query = "FROM Viewer v"),
    @NamedQuery(name = "Viewer.getById", query = "SELECT v FROM Viewer v WHERE v.id = :id ")
})
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = AnalysisViewer.class, name = "ANALYSIS"),
    @JsonSubTypes.Type(value = CombinedAnalysisViewer.class, name = "COMBINED"),
    @JsonSubTypes.Type(value = SingleSourceViewer.class, name = "SINGLESOURCE"),
    @JsonSubTypes.Type(value = SingleSourceGroupViewer.class, name = "SINGLESOURCE_GROUP")
})
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
@ConnectaViewer(module="presenter")
public class Viewer extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    
    @Column(name = "TP_VIEWER")
    @Enumerated(EnumType.STRING)
    private ViewerTypeEnum type;

    @Lob
    @Column(name = "BN_CONFIGURATION")
    private PresenterViewerConfiguration configuration;
    
    @Lob
    @Column(name = "BN_PREVIEW")
    private byte[] preview;
    
    @Column(name = "NM_DOMAIN")
    private String domain;

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

    public ViewerTypeEnum getType() {
        return type;
    }

    public void setType(ViewerTypeEnum type) {
        this.type = type;
    }

    public PresenterViewerConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(PresenterViewerConfiguration configuration) {
        this.configuration = configuration;
    }

    public byte[] getPreview() {
        return preview;
    }

    public void setPreview(byte[] preview) {
        this.preview = preview;
    }

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
