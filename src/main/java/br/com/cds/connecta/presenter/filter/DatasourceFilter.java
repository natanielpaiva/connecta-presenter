package br.com.cds.connecta.presenter.filter;

import br.com.cds.connecta.framework.core.search.filter.PaginationFilter;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class DatasourceFilter extends PaginationFilter {
    private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
    
    
}
