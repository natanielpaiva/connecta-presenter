package br.com.cds.connecta.presenter.entity.viewer;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

/**
 * 
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
@Entity
@Table(name = "TB_COMBINED_VIEWER")
@Indexed
public class CombinedViewer extends Viewer {
    
    /**
     * FIXME Tem que ter a lista dos outros viewers aqui, isso não é um visualizador de Análise Combinada
     */
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "FK_COMBINED_VIEWER")
//    private List<ViewerRelation> viewerRelations;
//    
//    public List<ViewerRelation> getViewerRelations() {
//        return viewerRelations;
//    }
//
//    public void setViewerRelations(List<ViewerRelation> viewerRelations) {
//        this.viewerRelations = viewerRelations;
//    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "FK_ANALYSIS")
//    private Analysis analysis;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "FK_COMBINED_ANALYSIS")
//    private CombinedAnalysis combinedAnalysis;
//
//    public Analysis getAnalysis() {
//        return analysis;
//    }
//
//    public void setAnalysis(Analysis analysis) {
//        this.analysis = analysis;
//    }
//
//    public CombinedAnalysis getCombinedAnalysis() {
//        return combinedAnalysis;
//    }
//
//    public void setCombinedAnalysis(CombinedAnalysis combinedAnalysis) {
//        this.combinedAnalysis = combinedAnalysis;
//    }
}
