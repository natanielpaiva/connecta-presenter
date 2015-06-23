package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;

public interface IAnalysisDAO extends IBaseJpaDAO<Analysis> {

	List<Analysis> list();
        
        Analysis getByIdColumns(Long id);

}
