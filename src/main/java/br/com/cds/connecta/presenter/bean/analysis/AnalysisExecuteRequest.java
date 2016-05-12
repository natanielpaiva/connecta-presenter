package br.com.cds.connecta.presenter.bean.analysis;

import br.com.cds.connecta.framework.core.search.filter.PaginationFilter;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import java.util.List;

/**
 * Wrapper de uma requisiçao de execuçao de analise
 * 
 * @author vinicius.pires
 */
public class AnalysisExecuteRequest {
    
    private Analysis analysis;
    private List<AnalysisFilter> filters;
    private PaginationFilter pagination;

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public List<AnalysisFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<AnalysisFilter> filters) {
        this.filters = filters;
    }

    public PaginationFilter getPagination() {
        return pagination;
    }

    public void setPagination(PaginationFilter pagination) {
        this.pagination = pagination;
    }
    
}
