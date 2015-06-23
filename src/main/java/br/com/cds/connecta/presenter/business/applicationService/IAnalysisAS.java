package br.com.cds.connecta.presenter.business.applicationService;

import java.util.List;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;

public interface IAnalysisAS {

    Analysis get(Long id);

    List<Analysis> list();

    Analysis saveOrUpdate(Analysis entity);
    
    void delete(Analysis analysis);

    void delete(Long id);

    Analysis getByIdColumns(Long id);

}
