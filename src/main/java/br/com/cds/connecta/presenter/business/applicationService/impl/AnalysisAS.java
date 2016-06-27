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
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisRelation;
import br.com.cds.connecta.presenter.filter.AnalysisFilter;
import br.com.cds.connecta.presenter.persistence.AnalysisRepository;
import br.com.cds.connecta.presenter.persistence.IAnalysisDAO;
import br.com.cds.connecta.presenter.persistence.specification.AnalysisSpecification;

@Service
public class AnalysisAS extends AbstractBaseAS<Analysis> implements IAnalysisAS {

    @Autowired
    private IAnalysisDAO analysisDAO;

    @Autowired
    private AnalysisRepository analysisRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Analysis get(Long id, String domain) {
        Analysis analysis = analysisRepository
                .findOne(AnalysisSpecification.byIdAndDomainWithCompleteFetch(id, domain));

        if (isNull(analysis)) {
            throw new ResourceNotFoundException(Analysis.class.getSimpleName());
        }
        
        List<AnalysisRelation> analysisRelations = analysisRepository.findRelationsById(analysis.getId());
        analysis.setAnalysisRelations(analysisRelations);
        
        return analysis;
    }

    @Override
    public Iterable<Analysis> list(AnalysisFilter filter) {
        Iterable<Analysis> analysisList;
        if (isNull(filter.getPage()) || isNull(filter.getCount())) {
            analysisList = analysisRepository.findAll(AnalysisSpecification.byDomain(filter.getDomain()));
        } else {
            Pageable pageable = filter.makePageable();
            analysisList = analysisRepository.findAll(AnalysisSpecification.byDomain(filter.getDomain()), pageable);
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
        Page<Analysis> analysisPage = analysisRepository
                .findAll(AnalysisSpecification.byNameAndDomainWithSimpleFetch(name,
                        filter.getDomain()), pageable);

        return analysisPage;
    }

    @Override
    public Analysis saveOrUpdate(Analysis analysis) {
        refreshAttribute(analysis);
        refreshAnalysisRelations(analysis);
        return analysisRepository.save(analysis);
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
    
    private void refreshAnalysisRelations(Analysis analysis) {
        if (isNotEmpty(analysis.getAnalysisRelations())) {
            for (AnalysisRelation analysisRelation : analysis.getAnalysisRelations()) {
                if (isNotNull(analysisRelation.getLeftAnalysisColumn()) &&
                    isNotNull(analysisRelation.getRightAnalysis()) &&
                    isNotNull(analysisRelation.getRightAnalysisColumn()) ) {
                    
                    Analysis rightAnalysis = em.find(Analysis.class, analysisRelation.getRightAnalysis().getId());
                    AnalysisColumn leftAnalysisColumn = em.find(AnalysisColumn.class, analysisRelation.getLeftAnalysisColumn().getId());
                    AnalysisColumn rightAnalysisColumn = em.find(AnalysisColumn.class, analysisRelation.getRightAnalysisColumn().getId());
                    
                    analysisRelation.setRightAnalysis(rightAnalysis);
                    analysisRelation.setLeftAnalysisColumn(leftAnalysisColumn);
                    analysisRelation.setRightAnalysisColumn(rightAnalysisColumn);
                }
            }
        }
    }

    @Override
    public void delete(Long id, String domain) {
        Analysis analysis = get(id, domain);
        analysisRepository.delete(analysis);
    }

    @Override
    public Analysis getByIdColumns(Long id) {
        return analysisDAO.getByIdColumns(id);
    }

    @Override
    public void deleteAll(List<Long> ids, String domain) {
        List<Analysis> listAnalysis = analysisRepository.findAll(AnalysisSpecification.byIdsAndDomain(ids, domain));
        analysisRepository.delete(listAnalysis);
    }

}
