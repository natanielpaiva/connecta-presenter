package br.com.cds.connecta.presenter.entity;

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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

/**
 * The persistent class for the TB_ANALYSIS_VIEWER database table.
 *
 */
@Entity
@Table(name = "TB_ANALYSIS_VIEWER")
@NamedQuery(name = "AnalysisViewer.findAll", query = "SELECT t FROM AnalysisViewer t")
public class AnalysisViewer extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_ANALYSIS_VIEWER_SEQ", sequenceName = "TB_ANALYSIS_VIEWER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_ANALYSIS_VIEWER_SEQ")
    @Column(name = "PK_ANALYSIS_VIEWER")
    private Long id;

    @Column(name = "NM_LABEL")
    private String label;

    @Column(name = "NU_INTRVL_ATLZCO")
    private Long intervalUpdateRow;

    @Column(name = "NU_MAX_LINHAS")
    private Long numMaxRow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_VIEWER")
    private Viewer viewer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ANALYSIS_VIEWER")
    private List<AnalysisVwColumn> analysisVwColumn;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getIntervalUpdateRow() {
        return intervalUpdateRow;
    }

    public Long getNumMaxRow() {
        return numMaxRow;
    }

    public void setNumMaxRow(Long numMaxRow) {
        this.numMaxRow = numMaxRow;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public List<AnalysisVwColumn> getAnalysisVwColumn() {
        return analysisVwColumn;
    }

    public void setAnalysisVwColumn(List<AnalysisVwColumn> analysisVwColumn) {
        this.analysisVwColumn = analysisVwColumn;
    }

    

}
