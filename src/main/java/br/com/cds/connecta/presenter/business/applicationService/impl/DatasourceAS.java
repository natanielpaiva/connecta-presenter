/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.framework.core.persistence.jpa.common.AbstractBaseJpaDAO;
import br.com.cds.connecta.presenter.business.applicationService.IDatasourceAS;
import br.com.cds.connecta.presenter.domain.DatasourceTypeEnum;
import br.com.cds.connecta.presenter.entity.datasource.ITypedDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class DatasourceAS implements IDatasourceAS {
    
    @Autowired
    private ApplicationContext context;

    @Override
    public ITypedDatasource save(ITypedDatasource datasource) {
        DatasourceTypeEnum type = datasource.getDatasource().getType();
        AbstractBaseJpaDAO dao = context.getBean(type.getDaoClass());
        
        AbstractBaseEntity datasourceEntity = (AbstractBaseEntity) datasource;
        
        return (ITypedDatasource) dao.saveOrUpdate(datasourceEntity);
    }

}
