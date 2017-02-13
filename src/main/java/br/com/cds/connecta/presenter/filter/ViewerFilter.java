package br.com.cds.connecta.presenter.filter;

import br.com.cds.connecta.framework.core.search.filter.PaginationFilter;

public class ViewerFilter extends PaginationFilter {
	
    private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
    
}
