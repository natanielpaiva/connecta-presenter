package br.com.cds.connecta.presenter.entity.analysis;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
@Entity
@Table(name = "TB_COMBINED_ANALYSIS")
@NamedQuery(name = "CombinedAnalysis.findAll", query = "SELECT t FROM CombinedAnalysis t")
public class CombinedAnalysis extends Analysis {

//    List<CombinedAnalysisNode>
    
    // MenAs é mais ;)
    
}
