package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.filter.AnalysisFilter;
import org.springframework.data.domain.Page;

public interface IAnalysisAS {

    Analysis get(Long id);

    Iterable<Analysis> list(AnalysisFilter filter);
    
    Page<Analysis> listAutoComplete(AnalysisFilter filter);

    Analysis saveOrUpdate(Analysis entity);
    
    void delete(Analysis analysis);

    void delete(Long id);

    Analysis getByIdColumns(Long id);

}
