package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.persistence.IAnalysisColumnDAO;

//APENAS OS MÉTODOS SALVA,ALTERAR,EXCLUIR SÃO PUBLICS ,OS OUTROS DEVEM SER IMPLEMENTADOS AQUI
@Repository
public class AnalysisColumnDAO extends AbstractBaseJpaDAO<AnalysisColumn> implements IAnalysisColumnDAO {

    @Override
    public AnalysisColumn get(Long id) {

        return (AnalysisColumn) getEntityManager().createNamedQuery("AnalysisColumn.get")
                .setParameter("id", id).getSingleResult();
    }

}
