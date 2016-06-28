package br.com.cds.connecta.presenter.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.business.applicationService.auth.IAuthAS;
import java.util.List;

public class AuthFilter extends GenericFilterBean {

    @Autowired
    private IAuthAS authAS;
    
    private List<String> exceptions;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!request.getMethod().equalsIgnoreCase("OPTIONS") 
                && !isPublic(request.getRequestURI())) {
            String token = request.getHeader("Authorization");

            if (Util.isNull(token) || !authAS.validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(req, res);
    }

    public boolean isPublic(String uri) {
        if (Util.isNotNull(uri)) {
            // adicionar uris de exceptions
            for (String ex : exceptions) {
                if (uri.contains(ex)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }
}
