package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the TB_WSO2_DS database table.
 *
 */
@Entity
@Table(name = "TB_WSO2_DS")
public class Wso2Datasource extends Datasource {

    @Column(name = "TXT_URL")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
