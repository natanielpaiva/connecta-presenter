/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.presenter.business.applicationService.IEndecaAS;
import br.com.cds.connecta.presenter.entity.datasource.EndecaDatasource;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.connector.endeca.service.ManageService;
import br.com.cds.connecta.framework.connector.endeca.service.SConfigService;
import br.com.cds.connecta.framework.connector.endeca.service.ServiceFault;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
@Service
public class EndecaAS implements IEndecaAS {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List getDomains(Long id) {

        List domains = null;
        EndecaDatasource endeca = em.find(EndecaDatasource.class, id);
        ManageService manageService = new ManageService(endeca.getAddress());
        try {
            domains = manageService.listDomains();
        } catch (ServiceFault ex) {
            Logger.getLogger(EndecaAS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return domains;
    }

    @Override
    public List<AnalysisColumn> getColumns(Long id, String domain) {
        ArrayList<AnalysisColumn> columns = new ArrayList<>();
        List domainColumns ;
        
        EndecaDatasource endeca = em.find(EndecaDatasource.class, id);
        
        SConfigService sconfig = new SConfigService(domain, endeca.getAddress());
       
        try {
             domainColumns = sconfig.getDomainColumns();
             for (Object domainColumn : domainColumns) {
                 AnalysisColumn column = new AnalysisColumn();
                 column.setFormula(domainColumn.toString());
                 column.setName(domainColumn.toString());
                 
                 columns.add(column);
            }
        } catch (ServiceFault ex) {
            Logger.getLogger(EndecaAS.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return columns;
    }

}
