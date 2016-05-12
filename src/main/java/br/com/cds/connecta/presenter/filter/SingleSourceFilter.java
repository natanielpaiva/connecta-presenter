/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.filter;

import br.com.cds.connecta.framework.core.search.filter.PaginationFilter;

/**
 *
 * @author nataniel
 */
public class SingleSourceFilter extends PaginationFilter {
    
    private String name;
    private String domain;
    
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
