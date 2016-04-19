package br.com.cds.connecta.presenter.entity.viewer;

import br.com.cds.connecta.presenter.entity.Filter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import org.hibernate.search.annotations.Indexed;

/**
 * The persistent class for the TB_ANALYSIS_VIEWER database table.
 *
 */
@Entity
@Table(name = "TB_ANALYSIS_VIEWER")
@NamedQuery(
        name = "AnalysisViewer.get",
        query = "SELECT a FROM AnalysisViewer a "
        + "LEFT JOIN FETCH a.analysisViewerColumns av "
        + "LEFT JOIN FETCH av.analysisColumn WHERE a.id = :id"
)
@Indexed
public class AnalysisViewer extends Viewer {

    @Column(name = "NM_LABEL")
    private String label;

    @Column(name = "NU_INTRVL_ATLZCO")
    private Long updateInterval;

    @Column(name = "NU_MAX_LINHAS")
    private Long maxRows;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ANALYSIS_VIEWER")
    private List<AnalysisViewerColumn> analysisViewerColumns;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ANALYSIS_VIEWER")
    private List<Filter> filters;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(Long updateInterval) {
        this.updateInterval = updateInterval;
    }

    public Long getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(Long maxRows) {
        this.maxRows = maxRows;
    }

    public List<AnalysisViewerColumn> getAnalysisViewerColumns() {
        return analysisViewerColumns;
    }

    public void setAnalysisViewerColumns(List<AnalysisViewerColumn> analysisViewerColumns) {
        this.analysisViewerColumns = analysisViewerColumns;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

}
