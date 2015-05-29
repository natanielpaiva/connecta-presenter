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

/**
 * The persistent class for the TA_ANALYSIS_VW_COLUMNS database table.
 *
 */
@Entity
@Table(name = "TA_ANALYSIS_VW_COLUMNS")
@NamedQuery(name = "AnalysisVwColumn.findAll", query = "SELECT t FROM AnalysisVwColumn t")
public class AnalysisVwColumn extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TA_ANALYSIS_VW_COLUMNS_SEQ", sequenceName = "TA_ANALYSIS_VW_COLUMNS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_ANALYSIS_VW_COLUMNS_SEQ")
    @Column(name = "PK_ANALYSIS_VW_COLUMNS")
    private Long id;

    @Column(name = "TP_COLUMN")
    private String typeColumn;

    @Column(name = "TP_ORDER")
    private String typeOrder;

    @Column(name = "TP_TYPE")
    private String type;

    @Column(name = "TXT_MASK_FORMAT")
    private String txtMaskFormat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ANALYSIS_COLUMNS")
    private AnalysisColumn analysisColumn;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeColumn() {
        return typeColumn;
    }

    public void setTypeColumn(String typeColumn) {
        this.typeColumn = typeColumn;
    }

    public String getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTxtMaskFormat() {
        return txtMaskFormat;
    }

    public void setTxtMaskFormat(String txtMaskFormat) {
        this.txtMaskFormat = txtMaskFormat;
    }

    public AnalysisColumn getAnalysisColumn() {
        return analysisColumn;
    }

    public void setAnalysisColumn(AnalysisColumn analysisColumn) {
        this.analysisColumn = analysisColumn;
    }

}
