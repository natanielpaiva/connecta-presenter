package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;

public interface IAnalysisDAO extends IBaseJpaDAO<Analysis> {

	List<Analysis> list();
        
        Analysis getByIdColumns(Long id);
        
        @Override
        Analysis get(Long id);
        
        
        DatabaseAnalysis getTest(Long id);
        
        

}
