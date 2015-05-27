package br.com.cds.connecta.presenter.business.applicationService;

import java.util.List;

import br.com.cds.connecta.presenter.entity.Analysis;
import br.com.cds.connecta.presenter.entity.AnalysisColumn;

public interface IAnalysisAS {

    Analysis get(Long id);

    List<Analysis> list();

    Analysis saveOrUpdate(Analysis entity);
    
    void delete(Analysis analysis);

    void delete(Long id);

    List<AnalysisColumn> getColumns(Long id);

}
