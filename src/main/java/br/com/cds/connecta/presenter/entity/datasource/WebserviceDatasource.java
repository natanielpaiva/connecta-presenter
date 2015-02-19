package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * The persistent class for the TB_WEBSERVICE_DS database table.
 *
 */
@Entity
@Table(name = "TB_WEBSERVICE_DS")
@NamedQuery(name = "WebserviceDatasource.findAll", query = "SELECT t FROM WebserviceDatasource t")
public class WebserviceDatasource extends AbstractBaseEntity implements ITypedDatasource {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FK_DATASOURCE")
    private Long id;

    @Column(name = "TXT_ADDRESS")
    private String address;

    @Column(name = "TXT_METHOD")
    private String method;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_DATASOURCE")
    @MapsId
    private Datasource datasource;

    @Transient
    @JsonIgnoreProperties(value = "webservice")
    private List<WebserviceDatasourceParameter> parameters;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public List<WebserviceDatasourceParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<WebserviceDatasourceParameter> parameters) {
        this.parameters = parameters;
    }
}
