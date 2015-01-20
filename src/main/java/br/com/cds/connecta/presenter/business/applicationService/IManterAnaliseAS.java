package br.com.cds.connecta.presenter.business.applicationService;

import java.util.List;

import br.com.cds.connecta.presenter.entity.Analysis;

public interface IManterAnaliseAS {
	
public abstract Analysis get(Long id) throws Exception;		

	
	public abstract List<Analysis> list() throws Exception;

	
	public abstract Analysis saveOrUpdate(Analysis entity) throws Exception; 
	
	
	public abstract void delete(Long id) throws Exception;
		

	public abstract void delete(Analysis analysi) throws Exception;
	
	
}