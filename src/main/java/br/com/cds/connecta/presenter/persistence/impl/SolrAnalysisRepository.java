package br.com.cds.connecta.presenter.persistence.impl;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.analysis.SolrAnalysis;
@Repository
public interface SolrAnalysisRepository extends JpaRepository<SolrAnalysis, Serializable> {
    
    
//    public SolrAnalysis gett(Long id) {
//
//        return (SolrAnalysis) getEntityManager().createNamedQuery("SolrAnalysis.get")
//                .setParameter("id", id).getSingleResult();
//    }

}
