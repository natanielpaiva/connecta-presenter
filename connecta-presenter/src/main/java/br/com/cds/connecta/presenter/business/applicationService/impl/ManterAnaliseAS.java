package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IManterAnaliseAS;
import br.com.cds.connecta.presenter.entity.Analysis;
import br.com.cds.connecta.presenter.persistence.IAnalysiDAO;

//REGRAS DE NEGÓCIO DO CASO DE USO

@Service
public class ManterAnaliseAS extends AbstractBaseAS<Analysis> implements IManterAnaliseAS{
	
	@Autowired
	private IAnalysiDAO analysiDAO;

	@Override
	public Analysis get(Long id) throws Exception {
		return getAnalysiDAO().get(id);
	}

	@Override
	public List<Analysis> list() throws Exception {
		return getAnalysiDAO().list();
	}

	@Override
	public Analysis saveOrUpdate(Analysis analysi) throws Exception {
		return getAnalysiDAO().saveOrUpdate(analysi);
	}

	@Override
	public void delete(Long id) throws Exception {
		getAnalysiDAO().delete(id);
	}
	
	@Override
	public void delete(Analysis analysi) throws Exception {
		getAnalysiDAO().delete(analysi);
	}
	
	public IAnalysiDAO getAnalysiDAO() {
		return analysiDAO;
	}

	public void setAnalysiDAO(IAnalysiDAO analysiDAO) {
		this.analysiDAO = analysiDAO;
	}


}
