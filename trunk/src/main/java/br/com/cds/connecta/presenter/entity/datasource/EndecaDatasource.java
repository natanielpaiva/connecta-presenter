package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TB_ENDECA_DS database table.
 *
 */
@Entity
@Table(name = "TB_ENDECA_DS")
@NamedQuery(name = "EndecaDatasource.findAll", query = "SELECT t FROM EndecaDatasource t")
public class EndecaDatasource extends Datasource {

    @Column(name = "TXT_ADDRESS")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
