package br.com.cds.connecta.presenter.business.applicationService.impl;

import static br.com.cds.connecta.framework.core.util.Util.isNotNull;
import static br.com.cds.connecta.framework.core.util.Util.isNull;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisAS;
import br.com.cds.connecta.presenter.entity.Attribute;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisAttribute;
import br.com.cds.connecta.presenter.filter.AnalysisFilter;
import br.com.cds.connecta.presenter.persistence.AnalysisRepository;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import br.com.cds.connecta.presenter.persistence.specification.AnalysisSpecification;

@Service
public class AnalysisAS extends AbstractBaseAS<Analysis> implements IAnalysisAS {

    @Autowired
    private IAnalysisDAO analysisDAO;

    @Autowired
    private AnalysisRepository analysisListRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Analysis get(Long id, String domain) {
        Analysis analysis = analysisListRepository
                .findOne(AnalysisSpecification.byIdAndDomainWithCompleteFetch(id, domain));

        if (isNull(analysis)) {
            throw new ResourceNotFoundException(Analysis.class.getSimpleName());
        }
        return analysis;
    }

    @Override
    public Iterable<Analysis> list(AnalysisFilter filter) {
        Iterable<Analysis> analysisList;
        if (isNull(filter.getPage()) || isNull(filter.getCount())) {
            analysisList = analysisListRepository.findAll(AnalysisSpecification.byDomain(filter.getDomain()));
        } else {
            Pageable pageable = filter.makePageable();
            analysisList = analysisListRepository.findAll(AnalysisSpecification.byDomain(filter.getDomain()), pageable);
        }

        return analysisList;
    }

    @Override
    public Page<Analysis> listAutoComplete(AnalysisFilter filter) {
        Pageable pageable = filter.makePageable();
        String name = filter.getName();
        if (isNull(name)) {
            name = "";
        }
        Page<Analysis> analyses = analysisListRepository
                .findAll(AnalysisSpecification.byNameAndDomainWithSimpleFetch(name,
                        filter.getDomain()), pageable);

        return analyses;
    }

    @Override
    public Analysis saveOrUpdate(Analysis analysis) {
        refreshAttribute(analysis);
        return analysisListRepository.save(analysis);
    }

    private void refreshAttribute(Analysis analysis) {
        if (isNotEmpty(analysis.getAnalysisAttributes())) {
            for (AnalysisAttribute analysisAttribute : analysis.getAnalysisAttributes()) {
                if (isNotNull(analysisAttribute.getAttribute()) && isNotNull(analysisAttribute.getAttribute().getId())) {
                    Attribute merge = em.merge(analysisAttribute.getAttribute());
                    analysisAttribute.setAttribute(merge);
                }
            }
        }

    }

    @Override
    public void delete(Long id, String domain) {
        Analysis analysis = get(id, domain);
        analysisListRepository.delete(analysis);
    }

    @Override
    public Analysis getByIdColumns(Long id) {
        return analysisDAO.getByIdColumns(id);
    }

    @Override
    public void deleteAll(List<Long> ids, String domain) {
        List<Analysis> listAnalysis = analysisListRepository.findAll(AnalysisSpecification.byIdsAndDomain(ids, domain));
        analysisListRepository.delete(listAnalysis);
    }

}
