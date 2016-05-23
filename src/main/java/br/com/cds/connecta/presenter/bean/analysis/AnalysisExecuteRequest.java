package br.com.cds.connecta.presenter.bean.analysis;

import java.util.List;

import br.com.cds.connecta.framework.core.search.filter.PaginationFilter;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;

/**
 * Wrapper de uma requisiçao de execuçao de analise
 * 
 * @author vinicius.pires
 */
public class AnalysisExecuteRequest {
    
    private Analysis analysis;
    private List<AnalysisFilter> filters;
    private PaginationFilter pagination;
    private AnalysisDrill drill;

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

    public AnalysisDrill getDrill() {
        return drill;
    }

    public void setDrill(AnalysisDrill drill) {
        this.drill = drill;
    }

}
