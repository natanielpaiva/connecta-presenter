package br.com.cds.connecta.presenter.entity.analysis;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;

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

    public Integer getOrderDrill() {
        return orderDrill;
    }

    public void setOrderDrill(Integer orderDrill) {
        this.orderDrill = orderDrill;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalysisColumn other = (AnalysisColumn) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
