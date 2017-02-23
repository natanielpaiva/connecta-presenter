package br.com.cds.connecta.presenter.entity.viewer;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author rsimplicio
 */
@Entity
@Table(name = "TB_GRAPH_VIEWER")
@NamedQuery(name = "GraphViewer.get", query = "SELECT a FROM GraphViewer a " 
        + "LEFT JOIN FETCH a.analysis an " +
            "LEFT JOIN FETCH an.datasource d WHERE a.id = :id ")
@Indexed
@SQLDelete(sql = "update TB_GRAPH_VIEWER set IS_ACTIVE = 0 where PK_VIEWER = ?")
@Where(clause = "IS_ACTIVE = 1")
public class GraphViewer extends Viewer {
    @Basic(optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ANALYSIS")
    private Analysis analysis;
    
    @Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
    private Boolean isActive = true;

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }
}
