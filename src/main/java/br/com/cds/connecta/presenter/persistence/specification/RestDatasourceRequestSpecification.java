package br.com.cds.connecta.presenter.persistence.specification;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.datasource.RestDatasourceRequest;
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
public class RestDatasourceRequestSpecification {

     public static Specification<RestDatasourceRequest> request() {
        return new Specification<RestDatasourceRequest>() {
            @Override
            public Predicate toPredicate(Root<RestDatasourceRequest> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                Class<?> clazz = query.getResultType();
                if (clazz.equals(RestDatasourceRequest.class)) {
                    root.fetch("headers", JoinType.LEFT);
                }
                return null;
            }
        };
    }
    
    public static Specification<RestDatasourceRequest> byId(final Long id) {
        return new Specification<RestDatasourceRequest>() {
            @Override
            public Predicate toPredicate(Root<RestDatasourceRequest> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.equal(root.<Long>get("id"), id);
            }
        };
    } 
     
    public static Specification<RestDatasourceRequest> byIdFetch(Long id) {
        return Specifications.where(byId(id)).and(request());
    } 
     
}
