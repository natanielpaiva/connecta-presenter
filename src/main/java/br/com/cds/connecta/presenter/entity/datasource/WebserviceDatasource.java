package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

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
    @SequenceGenerator(name = "TB_WEBSERVICE_DS_PKWEBSERVICEDS_GENERATOR", sequenceName = "TB_WEBSERVICE_DS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_WEBSERVICE_DS_PKWEBSERVICEDS_GENERATOR")
    @Column(name = "FK_DATASOURCE")
    private Long id;

    @Column(name = "TXT_ADDRESS")
    private String address;

    @Column(name = "TXT_METHOD")
    private String method;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_DATASOURCE")
    @MapsId
    private Datasource datasource;

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
}
