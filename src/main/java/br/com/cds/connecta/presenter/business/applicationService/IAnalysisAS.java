package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.filter.AnalysisFilter;
import java.util.List;
import org.springframework.data.domain.Page;

public interface IAnalysisAS {

    Analysis get(Long id, String domain);
    
    Iterable<Analysis> list(AnalysisFilter filter);
    
    Page<Analysis> listAutoComplete(AnalysisFilter filter);

    Analysis saveOrUpdate(Analysis entity);
    
    void delete(Long id, String domain);

    Analysis getByIdColumns(Long id);

    void deleteAll(List<Long> ids, String domain);
    
    Iterable<Analysis> listCached();

}
