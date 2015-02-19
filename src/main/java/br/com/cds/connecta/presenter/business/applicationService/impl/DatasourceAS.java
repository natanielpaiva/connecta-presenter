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
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import br.com.cds.connecta.presenter.entity.datasource.ITypedDatasource;
import br.com.cds.connecta.presenter.persistence.impl.DatasourceDAO;
import java.util.List;
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

    @Autowired
    private DatasourceDAO dao;

    @Override
    public ITypedDatasource save(ITypedDatasource datasource) {
        DatasourceTypeEnum type = datasource.getDatasource().getType();
        AbstractBaseJpaDAO Dao = context.getBean(type.getDaoClass());

        AbstractBaseEntity datasourceEntity = (AbstractBaseEntity) datasource;

        return (ITypedDatasource) Dao.saveOrUpdate(datasourceEntity);
    }

    @Override
    public List<Datasource> list() {
        return dao.list();
    }

    @Override
    public Datasource get(Long id) {
        return dao.get(id);
    }

    @Override
    public void delete(Long id) {
        Datasource ds = get(id);
       
        DatasourceTypeEnum type = ds.getType();
        AbstractBaseJpaDAO Dao = context.getBean(type.getDaoClass());
        
         AbstractBaseEntity datasourceEntity = (AbstractBaseEntity) Dao.get(ds.getId());;
         
        Dao.delete(datasourceEntity);
       // datasourceEntity.delete(ds);
        //dao.delete(ds);
    }

}
