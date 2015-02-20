package br.com.cds.connecta.presenter.entity.datasource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;



/**
 * The persistent class for the TB_WEBSERVICE_DS database table.
 *
 */
@Entity
@Table(name = "TB_WEBSERVICE_DS")
@NamedQuery(name = "WebserviceDatasource.findAll", query = "SELECT t FROM WebserviceDatasource t")
public class WebserviceDatasource extends Datasource {

    @Column(name = "TXT_ADDRESS")
    private String address;

    @Column(name = "TXT_METHOD")
    private String method;
    
    @Transient
    @JsonIgnoreProperties({"datasource"})
    private List<WebserviceDatasourceParameter> parameters;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<WebserviceDatasourceParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<WebserviceDatasourceParameter> parameters) {
        this.parameters = parameters;
    }
}
