package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TB_HDFS_DS database table.
 *
 */
@Entity
@Table(name = "TB_HDFS_DS")
@NamedQuery(name = "HDFSDatasource.findAll", query = "FROM HDFSDatasource t")
public class HDFSDatasource extends Datasource implements ITypedDatasource{

    @Column(name = "TXT_PATH")
    private String path;

    @Column(name = "TXT_PORT")
    private Integer port;

    @Column(name = "TXT_SERVER")
    private String server;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    /**
     * FIXME
     * @return 
     */
    @Override
    public Datasource getDatasource() {
        return null;
    }

}
