package br.com.cds.connecta.presenter.entity.datasource;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * The persistent class for the TB_DATABASE_DS database table.
 *
 */
@Entity
@Table(name = "TB_DATABASE_DS")
@NamedQuery(name = "DatabaseDatasource.findAll", query = "SELECT t FROM DatabaseDatasource t")
public class DatabaseDatasource extends AbstractBaseEntity implements ITypedDatasource {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FK_DATASOURCE")
    private Long id;

    @Column(name = "CD_DRIVER")
    @Enumerated(EnumType.STRING)
    private DatabaseDatasourceDriverEnum driver;

    @Column(name = "TXT_SERVER")
    private String server;
    
    @Column(name = "TXT_PORT")
    private BigDecimal port;
    
    @Column(name = "TXT_SID")
    private String sid;

    @Column(name = "TXT_SCHEMA")
    private String schema;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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

    public DatabaseDatasourceDriverEnum getDriver() {
        return driver;
    }

    public void setDriver(DatabaseDatasourceDriverEnum driver) {
        this.driver = driver;
    }

    public BigDecimal getPort() {
        return port;
    }

    public void setPort(BigDecimal port) {
        this.port = port;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

}
