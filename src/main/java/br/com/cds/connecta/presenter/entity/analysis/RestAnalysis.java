package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.presenter.entity.datasource.RestDatasourceRequest;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "TB_REST_ANALYSIS")
public class RestAnalysis extends Analysis {

    @Column(name = "TXT_TABLE_PATH")
    private String tablePath;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ANALYSIS", nullable = false)
    private List<RestRequestVariableAnalysis> requestVariables;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_REQUEST")
    private RestDatasourceRequest request;

    public RestDatasourceRequest getRequest() {
        return request;
    }

    public void setRequest(RestDatasourceRequest request) {
        this.request = request;
    }

   
    public String getTablePath() {
        return tablePath;
    }

    public void setTablePath(String tablePath) {
        this.tablePath = tablePath;
    }

    public List<RestRequestVariableAnalysis> getRequestVariables() {
        return requestVariables;
    }

    public void setRequestVariables(List<RestRequestVariableAnalysis> requestVariables) {
        this.requestVariables = requestVariables;
    }

}
