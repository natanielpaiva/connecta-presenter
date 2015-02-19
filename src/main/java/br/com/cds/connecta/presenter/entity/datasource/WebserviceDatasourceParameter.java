/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.entity.datasource;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the TB_WEBSERVICE_PARAMETER database table.
 *
 * @author diego
 */
@Entity
@Table(name = "TB_WEBSERVICE_PARAMETER")
public class WebserviceDatasourceParameter extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "TB_WEBSERVICE_PARAMETERS_SEQ",
            sequenceName = "TB_WEBSERVICE_PARAMETERS_SEQ",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_WEBSERVICE_PARAMETERS_SEQ")
    @Column(name = "PK_WEBSERVICE_PARAMETER")
    private Long id;

    @Column(name = "TXT_PARAMS")
    private String params;

    @Column(name = "TXT_VALUE")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_WEBSERVICE_DS", referencedColumnName = "PK_DATASOURCE")
    private WebserviceDatasource webservice;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = Id;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public WebserviceDatasource getWebservice() {
        return webservice;
    }

    public void setWebservice(WebserviceDatasource webservice) {
        this.webservice = webservice;
    }

}
