package br.com.cds.connecta.presenter.persistence.specification;

import br.com.cds.connecta.presenter.entity.analysis.RestAnalysis;
import static br.com.cds.connecta.presenter.persistence.specification.AnalysisSpecification.isCached;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

/**
 *
 * @author diego
 */
public class RestAnalysisSpecification {
    
    public static Specification<RestAnalysis> byId(final Long id) {
        return new Specification<RestAnalysis>() {
            @Override
            public Predicate toPredicate(Root<RestAnalysis> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.equal(root.<Long>get("id"), id);
            }
        };
    }
    
     public static Specification<RestAnalysis> fetchCompleteData() {
        return new Specification<RestAnalysis>() {
            @Override
            public Predicate toPredicate(Root<RestAnalysis> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                Class<?> clazz = query.getResultType();
                if (clazz.equals(RestAnalysis.class)) {
                    root.fetch("request", JoinType.LEFT);
                    root.fetch("requestVariables", JoinType.LEFT);
                }
                return null;
            }
        };
    }
     
      public static Specification<RestAnalysis> getRestAnalyis(Long id) {
        return Specifications.where(byId(id)).and(fetchCompleteData());
    }
    

}
