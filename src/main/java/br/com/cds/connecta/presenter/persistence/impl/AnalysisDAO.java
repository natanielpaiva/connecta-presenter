package br.com.cds.connecta.presenter.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;

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

}
