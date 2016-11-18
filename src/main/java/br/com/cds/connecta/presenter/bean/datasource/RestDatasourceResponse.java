package br.com.cds.connecta.presenter.bean.datasource;

import br.com.cds.connecta.framework.connector2.context.http.Header;
import br.com.cds.connecta.framework.connector2.context.http.HttpResponse;
import br.com.cds.connecta.presenter.bean.analysis.json.JSONValue;
import java.util.List;

/**
 *
 * @author diego
 */
public class RestDatasourceResponse {
    
    private List<Header> headers ;
    private String body;
    private String statusCode;
    private JSONValue bodySpecified;

    public RestDatasourceResponse() {}

    public RestDatasourceResponse(HttpResponse httpResponse) {
        this.headers = httpResponse.getHeaders();
        this.body = httpResponse.getBody();
        this.statusCode = httpResponse.getStatusCode();
        
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }
    
    
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public JSONValue getBodySpecified() {
        return bodySpecified;
    }

    public void setBodySpecified(JSONValue bodySpecified) {
        this.bodySpecified = bodySpecified;
    }

}