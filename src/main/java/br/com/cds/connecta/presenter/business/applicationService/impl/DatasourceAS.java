package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import static br.com.cds.connecta.framework.core.util.Util.isNull;
import br.com.cds.connecta.presenter.business.applicationService.IDatasourceAS;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasourceParameter;
import br.com.cds.connecta.presenter.filter.DatasourceFilter;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import br.com.cds.connecta.presenter.persistence.impl.BulkActionsRepository;
import br.com.cds.connecta.presenter.persistence.impl.WebserviceDatasourceParameterDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class DatasourceAS implements IDatasourceAS {

    @Autowired
    private DatasourceRepository dao;
    
    @Autowired
    private BulkActionsRepository<Long> bulk;

    @Autowired
    private WebserviceDatasourceParameterDAO parameterDAO;

    @Override
    public Datasource save(Datasource datasource) {
        return dao.save(datasource);
    }
    
    @Override
    public Iterable<Datasource> list(DatasourceFilter filter) {
        Pageable pageable = filter.makePageable();
        return dao.findAll(pageable);
    }

    @Override
    public Datasource get(Long id) {
        Datasource ds = dao.findOne(id);
        
        if (isNull(ds)) {
            throw new ResourceNotFoundException(Datasource.class.getCanonicalName());
        }

        if (ds instanceof WebserviceDatasource) {
            WebserviceDatasource wds = (WebserviceDatasource) ds;
            List<WebserviceDatasourceParameter> parameters = parameterDAO.findAllByWebserviceDatasource(wds);
            wds.setParameters(parameters);
            return wds;
        } else {
            return ds;
        }
    }

    @Override
    public void delete(Long id) {
        Datasource ds = get(id);

        if (ds instanceof WebserviceDatasource) {
            WebserviceDatasource wds = (WebserviceDatasource) ds;
            _delete(wds);
        } else {
            _delete(ds);
        }
    }
    
    @Override
    public void deleteAll(List<Long> ids) {
        bulk.delete(Datasource.class, ids);
    }

    private void _delete(WebserviceDatasource ds) {
        List<WebserviceDatasourceParameter> parameters = parameterDAO.findAllByWebserviceDatasource(ds);
        parameterDAO.deleteAll(parameters);
        dao.delete(ds);
    }

    private void _delete(Datasource ds) {
        dao.delete(ds);
    }

}
