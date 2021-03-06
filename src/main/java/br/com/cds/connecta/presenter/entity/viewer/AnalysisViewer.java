package br.com.cds.connecta.presenter.entity.viewer;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.search.annotations.Indexed;

/**
 * The persistent class for the TB_ANALYSIS_VIEWER database table.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TB_ANALYSIS_VIEWER")
@NamedQuery(name = "AnalysisViewer.get", query = "SELECT a FROM AnalysisViewer a " + "LEFT JOIN FETCH a.analysis an "
		+ "LEFT JOIN FETCH an.datasource d " + "LEFT JOIN FETCH a.analysisViewerColumns av "
		+ "LEFT JOIN FETCH av.analysisColumn WHERE a.id = :id")
@Indexed
@SQLDelete(sql = "update TB_ANALYSIS_VIEWER set IS_ACTIVE = 0 where PK_VIEWER = ?")
@Where(clause = "IS_ACTIVE = 1")
public class AnalysisViewer extends Viewer {

	@Column(name = "NM_LABEL")
	private String label;

	@Column(name = "NU_INTRVL_ATLZCO")
	private Long updateInterval;

	@Column(name = "NU_MAX_LINHAS")
	private Long maxRows;

	@Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
	private Boolean isActive = true;

	@Basic(optional = false)
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ANALYSIS")
	private Analysis analysis;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "FK_ANALYSIS_VIEWER")
	private List<AnalysisViewerColumn> analysisViewerColumns;

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

	public Analysis getAnalysis() {
		return analysis;
	}

	public void setAnalysis(Analysis analysis) {
		this.analysis = analysis;
	}

	public List<AnalysisViewerColumn> getAnalysisViewerColumns() {
		return analysisViewerColumns;
	}

	public void setAnalysisViewerColumns(List<AnalysisViewerColumn> analysisViewerColumns) {
		this.analysisViewerColumns = analysisViewerColumns;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
