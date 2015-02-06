package br.com.cds.connecta.presenter.entity.datasource;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;

/**
 * The persistent class for the TB_HDFS_DS database table.
 *
 */
@Entity
@Table(name = "TB_HDFS_DS")
@NamedQuery(name = "HDFSDatasource.findAll", query = "FROM HDFSDatasource t")
public class HDFSDatasource extends AbstractBaseEntity implements ITypedDatasource {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_HDFS_DS_PKHDFSDS_GENERATOR", sequenceName = "TB_HDFS_DS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_HDFS_DS_PKHDFSDS_GENERATOR")
    @Column(name = "PK_HDFS_DS")
    private Long id;

    @Column(name = "TXT_PATH")
    private String path;

    @Column(name = "TXT_PORT")
    private BigDecimal port;

    @Column(name = "TXT_SERVER")
    private String server;

    //bi-directional many-to-one association to TbDatasource
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_DATASOURCE")
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

    public BigDecimal getPort() {
        return port;
    }

    public void setPort(BigDecimal port) {
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

}
