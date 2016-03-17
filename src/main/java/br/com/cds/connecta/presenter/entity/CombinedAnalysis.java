package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;

/**
 * The persistent class for the TB_COMBINED_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_COMBINED_ANALYSIS")
@NamedQuery(name = "CombinedAnalysis.findAll", query = "SELECT t FROM CombinedAnalysis t")
public class CombinedAnalysis extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_COMBINED_ANALYSIS")
    private Long id;

    @Column(name = "DS_COMBINED_ANALYSIS")
    private String dsCombinedAnalysis;

    @Column(name = "NM_COMBINED_ANALYSIS")
    private String nmCombinedAnalysis;

    public CombinedAnalysis() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsCombinedAnalysis() {
        return this.dsCombinedAnalysis;
    }

    public void setDsCombinedAnalysis(String dsCombinedAnalysis) {
        this.dsCombinedAnalysis = dsCombinedAnalysis;
    }

    public String getNmCombinedAnalysis() {
        return this.nmCombinedAnalysis;
    }

    public void setNmCombinedAnalysis(String nmCombinedAnalysis) {
        this.nmCombinedAnalysis = nmCombinedAnalysis;
    }
}
