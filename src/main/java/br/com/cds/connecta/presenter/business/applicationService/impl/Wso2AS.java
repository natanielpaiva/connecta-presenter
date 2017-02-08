package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector2.context.wso2.Wso2DataContextFactory;
import br.com.cds.connecta.presenter.business.applicationService.IWso2AS;
import br.com.cds.connecta.presenter.entity.datasource.Wso2Datasource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class Wso2AS implements IWso2AS {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String[] getTables(Long id) {

        Wso2Datasource wso2 = em.find(Wso2Datasource.class, id);

        Wso2DataContextFactory wso2DataContextFactory = new Wso2DataContextFactory(wso2.getUser(), wso2.getPassword(), wso2.getUrl());

        return wso2DataContextFactory.getTables();

    }

}
