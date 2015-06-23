package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.presenter.domain.WebserviceAnalysisTypeEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


/**
 * The persistent class for the TB_WEBSERVICE_ANALYSIS database table.
 *
 */
@Entity
@Table(name = "TB_WEBSERVICE_ANALYSIS")
public class WebserviceAnalysis extends Analysis {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_WS")
    private WebserviceAnalysisTypeEnum webserviceType;
    
    @Column(name = "TXT_TABLE_PATH")
    private String tablePath;

    @Column(name = "TXT_METHOD")
    private String method;

    @Column(name = "TXT_PARAMS")
    private String params;

    public WebserviceAnalysisTypeEnum getWebserviceType() {
        return webserviceType;
    }

    public void setWebserviceType(WebserviceAnalysisTypeEnum webserviceType) {
        this.webserviceType = webserviceType;
    }

    public String getTablePath() {
        return tablePath;
    }

    public void setTablePath(String tablePath) {
        this.tablePath = tablePath;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
    
    

}
