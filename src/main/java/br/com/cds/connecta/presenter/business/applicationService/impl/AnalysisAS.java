package br.com.cds.connecta.presenter.business.applicationService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import static br.com.cds.connecta.framework.core.util.Util.isNull;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisAS;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import br.com.cds.connecta.presenter.filter.AnalysisFilter;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import br.com.cds.connecta.presenter.persistence.AnalysisRepository;
import br.com.cds.connecta.presenter.persistence.impl.BulkActionsRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AnalysisAS extends AbstractBaseAS<Analysis> implements IAnalysisAS {

    @Autowired
    private BulkActionsRepository<Long> bulk;
    
    @Autowired
    private IAnalysisDAO analysisDAO;
    
    @Autowired
    private AnalysisRepository analysisListRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Analysis get(Long id) {
        Analysis analysis;
        
        try {
            analysis = analysisDAO.get(id);
        } catch(NoResultException|EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException(Analysis.class.getCanonicalName());
        }
        
        return analysis;
    }
    
    @Override
    public DatabaseAnalysis getTest(Long id) {
        return analysisDAO.getTest(id);
    }

    @Override
    public Iterable<Analysis> list(AnalysisFilter filter) {
        Pageable pageable = filter.makePageable();
        return analysisListRepository.findAll(pageable);

    }

    @Override
    public Page<Analysis> listAutoComplete(AnalysisFilter filter) {
        String name = filter.getName();
        if (isNull(name)) {
            name = "";
        }
        Page<Analysis> analysises = analysisListRepository.findByName("%" + name.toUpperCase() + "%", filter.makePageable());

        for (Analysis analysise : analysises) {
            Analysis analysis = analysisDAO.get(analysise.getId());
            analysise.setDatasource(analysis.getDatasource());
            analysise.setAnalysisColumns(analysis.getAnalysisColumns());
        }

        return analysises;
    }

    @Override
    public Analysis saveOrUpdate(Analysis analysis) {
        return analysisDAO.saveOrUpdate(analysis);
    }

    @Override
    public void delete(Long id) {
        Analysis analysis = em.find(Analysis.class, id);
        em.remove(analysis);
    }

    @Override
    public void delete(Analysis analysi) {
        analysisDAO.delete(analysi);
    }

    @Override
    public Analysis getByIdColumns(Long id) {
        return analysisDAO.getByIdColumns(id);
    }

    @Override
    public void deleteAll(List<Long> ids) {
        bulk.delete(Analysis.class, ids);
    }

}
