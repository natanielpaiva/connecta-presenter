package br.com.cds.connecta.presenter.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.Analysis;
import br.com.cds.connecta.presenter.persistence.IAnalysiDAO;

//APENAS OS MÉTODOS SALVA,ALTERAR,EXCLUIR SÃO PUBLICS ,OS OUTROS DEVEM SER IMPLEMENTADOS AQUI

@Repository
public class AnalysiDAO extends AbstractBaseJpaDAO<Analysis> implements IAnalysiDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Analysis> list() {
		return getEntityManager().createNamedQuery("Analysi.findAll").getResultList();
	}
	

}
