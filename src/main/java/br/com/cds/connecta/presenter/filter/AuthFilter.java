package br.com.cds.connecta.presenter.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.business.applicationService.auth.IAuthAS;

public class AuthFilter extends GenericFilterBean {

    @Autowired
    private IAuthAS authAS;
    
    /**
     * Preenchida no application-context.xml
     */
    private List<String> exceptions;
    
    private static final String authCookie = "portal.auth.access_token";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if ( !request.getMethod().equalsIgnoreCase("OPTIONS") &&
             !isPublic(request.getServletPath()) ) {
            
        	String headerToken = request.getHeader("Authorization");
        	
            String token = headerToken == null ? getTokenFromCookie(request) : headerToken;

            if (Util.isNull(token) || !authAS.validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(req, res);
    }
    
	private String getTokenFromCookie(HttpServletRequest request) {
		for (Cookie cookie : request.getCookies()) {
			if (authCookie.equals(cookie.getName())) {
				String cookieValue = cookie.getValue();
				if (cookieValue.startsWith("%22") && cookieValue.endsWith("%22")) {
					return "Bearer " + cookieValue.substring(3, cookieValue.length() - 3);
				}
			}
		}
		return null;
	}

    public boolean isPublic(String uri) {
        if (Util.isNotNull(uri)) {
            // adicionar uris de exceptions
            for (String ex : exceptions) {
                if (uri.equals(ex)) {
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
