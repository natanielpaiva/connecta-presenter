package br.com.cds.connecta.presenter.entity.analysis;

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

/**
 * The persistent class for the TB_RLTN_CMB_ANALYSIS_JOIN database table.
 *
 */
@Entity
@Table(name = "TB_RLTN_CMB_ANALYSIS_JOIN")
public class CombinedAnalysisNode extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_LYR_CMB_ANALYSIS_JOIN")
    private Long id;

    @Column(name = "NM_COLUMN_JOIN1")
    private String nmColumnJoin1;

    @Column(name = "NM_COLUMN_JOIN2")
    private String nmColumnJoin2;

    //bi-directional many-to-one association to TbRelationCmbAnalysi
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PK_RELATION_CMB_ANALYSIS")
    private AnalysisRelation tbRelationCmbAnalysi;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmColumnJoin1() {
        return this.nmColumnJoin1;
    }

    public void setNmColumnJoin1(String nmColumnJoin1) {
        this.nmColumnJoin1 = nmColumnJoin1;
    }

    public String getNmColumnJoin2() {
        return this.nmColumnJoin2;
    }

    public void setNmColumnJoin2(String nmColumnJoin2) {
        this.nmColumnJoin2 = nmColumnJoin2;
    }

    public AnalysisRelation getTbRelationCmbAnalysi() {
        return this.tbRelationCmbAnalysi;
    }

    public void setTbRelationCmbAnalysi(AnalysisRelation tbRelationCmbAnalysi) {
        this.tbRelationCmbAnalysi = tbRelationCmbAnalysi;
    }

}
