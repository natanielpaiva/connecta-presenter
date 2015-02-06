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
 * The persistent class for the TB_HDFS_DS database table.
 *
 */
@Entity
@Table(name = "TB_HDFS_DS")
@NamedQuery(name = "HDFSDatasource.findAll", query = "FROM HDFSDatasource t")
public class HDFSDatasource extends AbstractBaseEntity implements ITypedDatasource {

    @Id
    @SequenceGenerator(name = "TB_HDFS_DS_SEQ", sequenceName = "TB_HDFS_DS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_HDFS_DS_SEQ")
    @Column(name = "FK_DATASOURCE")
    private Long id;

    @Column(name = "TXT_PATH")
    private String path;

    @Column(name = "TXT_PORT")
    private Integer port;

    @Column(name = "TXT_SERVER")
    private String server;

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

    @Override
    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

}
