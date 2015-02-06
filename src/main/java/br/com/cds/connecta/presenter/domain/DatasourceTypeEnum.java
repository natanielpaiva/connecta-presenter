/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.domain;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.entity.datasource.BIDatasource;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.persistence.impl.BIDatasourceDAO;
import br.com.cds.connecta.presenter.persistence.impl.DatabaseDatasourceDAO;
import br.com.cds.connecta.presenter.persistence.impl.EndecaDatasourceDAO;
import br.com.cds.connecta.presenter.persistence.impl.HDFSDatasourceDAO;
import br.com.cds.connecta.presenter.persistence.impl.SolrDatasourceDAO;
import br.com.cds.connecta.presenter.persistence.impl.WebserviceDSDAO;

/**
 *
 * @author diego
 */
public enum DatasourceTypeEnum {

    DATABASE(DatabaseDatasourceDAO.class),
    ENDECA(EndecaDatasourceDAO.class),
    HDFS(HDFSDatasourceDAO.class),
    BI(BIDatasourceDAO.class),
    SOLR(SolrDatasourceDAO.class),
    WEBSERVICE(WebserviceDSDAO.class);
    
    private final Class<? extends AbstractBaseJpaDAO> daoClass;
    
    private DatasourceTypeEnum(Class<? extends AbstractBaseJpaDAO> dao) {
        this.daoClass = dao;
    }

    public Class<? extends AbstractBaseJpaDAO> getDaoClass() {
        return daoClass;
    }
    
}
