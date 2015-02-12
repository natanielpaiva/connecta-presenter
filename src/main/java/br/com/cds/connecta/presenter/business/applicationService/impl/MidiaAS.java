package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IMidiaAS;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.persistence.ISingleSourceDAO;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MidiaAS extends AbstractBaseAS<SingleSource> implements IMidiaAS {

    @Autowired
    private ISingleSourceDAO singleSourceDAO;
    
    @Override
    public List<SingleSource> list() throws Exception {
        return singleSourceDAO.list();
    }

    @Override
    public SingleSource saveOrUpdate(SingleSource singleSource) throws Exception {
        return singleSourceDAO.saveOrUpdate(singleSource);
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(SingleSource entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SingleSource get(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
