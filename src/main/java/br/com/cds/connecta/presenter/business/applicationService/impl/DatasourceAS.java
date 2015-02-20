/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.presenter.business.applicationService.IDatasourceAS;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasourceParameter;
import br.com.cds.connecta.presenter.persistence.impl.DatasourceDAO;
import br.com.cds.connecta.presenter.persistence.impl.WebserviceDatasourceParameterDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class DatasourceAS implements IDatasourceAS {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DatasourceDAO dao;
    
    @Autowired
    private WebserviceDatasourceParameterDAO parameterDAO;

    @Override
    public Datasource save(Datasource datasource) {
        return dao.saveOrUpdate(datasource);
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
        
        if ( ds instanceof WebserviceDatasource ) {
            WebserviceDatasource wds = (WebserviceDatasource) ds;
            _delete(wds);
        } else {
            _delete(ds);
        }
    }
    
    private void _delete(WebserviceDatasource ds) {
        List<WebserviceDatasourceParameter> parameters = parameterDAO.findAllByWebserviceDatasource(ds);
        parameterDAO.deleteAll(parameters);
        dao.delete(ds);
    }

    private void _delete(Datasource ds) {
        dao.delete(ds);
    }

    @Override
    public Datasource saveWebservice(WebserviceDatasource datasource) {
        WebserviceDatasource entity = (WebserviceDatasource) save(datasource);
        
        for (WebserviceDatasourceParameter parameter : entity.getParameters()) {
            parameter.setDatasource(entity);
            em.persist(parameter);
        }
        
        return entity;
    }


    
    
    
    
    
    

}
