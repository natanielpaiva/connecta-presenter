package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.analysis.SolrAnalysis;
@Repository
public class SolrAnalysiDAO extends AbstractBaseJpaDAO<SolrAnalysis> {
    
    
//    public SolrAnalysis gett(Long id) {
//
//        return (SolrAnalysis) getEntityManager().createNamedQuery("SolrAnalysis.get")
//                .setParameter("id", id).getSingleResult();
//    }

}
