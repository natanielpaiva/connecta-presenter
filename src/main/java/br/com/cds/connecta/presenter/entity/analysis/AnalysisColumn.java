package br.com.cds.connecta.presenter.entity.analysis;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewerColumn;

import javax.persistence.OneToMany;

/**
 * The persistent class for the TB_ANALYSIS_COLUMNS database table.
 *
 */
@Entity
@Table(name = "TB_ANALYSIS_COLUMNS")
//@DynamicUpdate
public class AnalysisColumn extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ANALYSIS_COLUMNS")
    private Long id;

    @Column(name = "FML_COLUMN")
    private String formula;

    @Column(name = "LB_COLUMN")
    private String label;

    @Column(name = "NM_COLUMN")
    private String name;

    @Column(name = "ORDER_DRILL")
    private Integer orderDrill;

    @Column(name = "TP_COLUMN")
    private BigDecimal type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ANALYSIS_COLUMNS")
    private List<AnalysisViewerColumn> analysisViewerColumns;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public int getOrderDrill() {
        return orderDrill;
    }

    public void setOrderDrill(Integer orderDrill) {
        this.orderDrill = orderDrill;
    }

    public List<AnalysisViewerColumn> getAnalysisViewerColumns() {
        return analysisViewerColumns;
    }

    public void setAnalysisViewerColumns(List<AnalysisViewerColumn> analysisViewerColumns) {
        this.analysisViewerColumns = analysisViewerColumns;
    }

}
