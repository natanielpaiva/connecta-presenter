package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TB_SOLR_DS database table.
 *
 */
@Entity
@Table(name = "TB_SOLR_DS")
@NamedQuery(name = "SolrDatasource.findAll", query = "SELECT t FROM SolrDatasource t")
public class SolrDatasource extends Datasource {

    @Column(name = "TXT_ADDRESS")
    private String address;

    @Column(name = "TXT_PATH")
    private String path;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
