package br.com.cds.connecta.presenter.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import org.hibernate.Hibernate;

//APENAS OS MÉTODOS SALVA,ALTERAR,EXCLUIR SÃO PUBLICS ,OS OUTROS DEVEM SER IMPLEMENTADOS AQUI
@Repository
public class AnalysisDAO extends AbstractBaseJpaDAO<Analysis> implements IAnalysisDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Analysis> list() {
        return getEntityManager().createNamedQuery("Analysis.findAll").getResultList();
    }

    @Override
    public Analysis getByIdColumns(Long id) {
        
        return (Analysis) getEntityManager().createNamedQuery("Analysis.findById")
                .setParameter("id", id).setMaxResults(1).getSingleResult();
    }
    
    @Override
    public Analysis get(Long id){
        
         Analysis analysis =  (Analysis) getEntityManager().createNamedQuery("Analysis.find")
                .setParameter("id", id).getSingleResult();
        
        Hibernate.initialize(analysis.getAnalysisColumns());
        return analysis;
    }
    
    
    @Override
    public DatabaseAnalysis getTest(Long id){
        return (DatabaseAnalysis) getEntityManager().createNamedQuery("DatabaseAnalysis.get")
                .setParameter("id", id).getSingleResult();
    }

}
