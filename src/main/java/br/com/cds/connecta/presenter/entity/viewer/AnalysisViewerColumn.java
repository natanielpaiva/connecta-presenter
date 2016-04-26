package br.com.cds.connecta.presenter.entity.viewer;

import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.domain.AnalysisViewerColumnDataType;
import br.com.cds.connecta.presenter.domain.AnalysisViewerColumnOrderEnum;
import br.com.cds.connecta.presenter.domain.AnalysisViewerColumnType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * The persistent class for the TA_ANALYSIS_VW_COLUMNS database table.
 */
@Entity
@Table(name = "TA_ANALYSIS_VW_COLUMNS")
public class AnalysisViewerColumn extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ANALYSIS_VW_COLUMNS")
    private Long id;
    
    @Column(name = "TP_TYPE")
    @Enumerated(EnumType.STRING)
    private AnalysisViewerColumnType columnType;

    @Column(name = "TP_COLUMN")
    @Enumerated(EnumType.STRING)
    private AnalysisViewerColumnDataType columnDataType;

    @Column(name = "TP_ORDER")
    @Enumerated(EnumType.STRING)
    private AnalysisViewerColumnOrderEnum order;

    @Column(name = "TXT_MASK_FORMAT")
    private String maskFormat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_ANALYSIS_COLUMNS")
    private AnalysisColumn analysisColumn;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnalysisViewerColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(AnalysisViewerColumnType columnType) {
        this.columnType = columnType;
    }

    public AnalysisViewerColumnDataType getColumnDataType() {
        return columnDataType;
    }

    public void setColumnDataType(AnalysisViewerColumnDataType columnDataType) {
        this.columnDataType = columnDataType;
    }

    public AnalysisViewerColumnOrderEnum getOrder() {
        return order;
    }

    public void setOrder(AnalysisViewerColumnOrderEnum order) {
        this.order = order;
    }

    public String getMaskFormat() {
        return maskFormat;
    }

    public void setMaskFormat(String maskFormat) {
        this.maskFormat = maskFormat;
    }

    public AnalysisColumn getAnalysisColumn() {
        return analysisColumn;
    }

    public void setAnalysisColumn(AnalysisColumn analysisColumn) {
        this.analysisColumn = analysisColumn;
    }
}
