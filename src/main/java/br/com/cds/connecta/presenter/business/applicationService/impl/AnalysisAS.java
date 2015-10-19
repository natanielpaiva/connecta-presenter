package br.com.cds.connecta.presenter.business.applicationService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import static br.com.cds.connecta.framework.core.util.Util.isNull;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisAS;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.DatabaseAnalysis;
import br.com.cds.connecta.presenter.filter.AnalysisFilter;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import br.com.cds.connecta.presenter.persistence.AnalysisRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AnalysisAS extends AbstractBaseAS<Analysis> implements IAnalysisAS {

    @Autowired
    private IAnalysisDAO analysisDAO;

    @Autowired
    private AnalysisRepository analysisListRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Analysis get(Long id) {
        return analysisDAO.get(id);
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

}
