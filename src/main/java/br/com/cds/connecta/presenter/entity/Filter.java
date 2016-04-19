package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.domain.AppearanceEnum;
import br.com.cds.connecta.presenter.domain.FilterTypeEnum;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_FILTER")
public class Filter extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_FILTER")
    private Long id;

    @Column(name = "NM_FILTER")
    private String name;

    @Column(name = "TP_FILTER")
    @Enumerated(value = EnumType.STRING)
    private FilterTypeEnum type;

    @Column(name = "APPEARANCE")
    @Enumerated(value = EnumType.STRING)
    private AppearanceEnum appearance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ANALYSIS_COLUMNS")
    private AnalysisColumn analysisColumn;

    @OneToMany
    @JoinColumn(name = "PK_FILTER")
    private List<StaticFilter> staticFilterValue;

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

    public FilterTypeEnum getType() {
        return type;
    }

    public void setType(FilterTypeEnum type) {
        this.type = type;
    }

    public AppearanceEnum getAppearance() {
        return appearance;
    }

    public void setAppearance(AppearanceEnum appearance) {
        this.appearance = appearance;
    }

    public AnalysisColumn getAnalysisColumn() {
        return analysisColumn;
    }

    public void setAnalysisColumn(AnalysisColumn analysisColumn) {
        this.analysisColumn = analysisColumn;
    }

    public List<StaticFilter> getStaticFilterValue() {
        return staticFilterValue;
    }

    public void setStaticFilterValue(List<StaticFilter> staticFilterValue) {
        this.staticFilterValue = staticFilterValue;
    }

}
