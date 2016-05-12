package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import javax.persistence.Basic;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;

/**
 * The persistent class for the TB_DATABASE_DS database table.
 *
 */
@Entity
@Table(name = "TB_DATABASE_DS")
@NamedQueries({
    @NamedQuery(name = "DatabaseDatasource.findById", query = "SELECT t FROM DatabaseDatasource t  WHERE t.id = :id "),
    @NamedQuery(name = "DatabaseDatasource.findAll", query = "SELECT t FROM DatabaseDatasource t")
})
public class DatabaseDatasource extends Datasource {
   
    @Column(name = "CD_DRIVER")
    @Enumerated(EnumType.STRING)
    private DatabaseDatasourceDriverEnum driver;

    @Column(name = "TXT_SERVER")
    private String server;
    
    @Column(name = "TXT_PORT")
    private Integer port;
    
    @Column(name = "TXT_SID")
    private String sid;

    @Basic(optional = true)
    @Column(name = "TXT_SCHEMA")
    private String schema;

    public DatabaseDatasourceDriverEnum getDriver() {
        return driver;
    }

    public void setDriver(DatabaseDatasourceDriverEnum driver) {
        this.driver = driver;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
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
}