package br.com.cds.connecta.presenter.persistence.specification;

import br.com.cds.connecta.presenter.entity.datasource.RestDatasource;
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
public class RestDatasourceSpecification {

     public static Specification<RestDatasource> rest() {
        return new Specification<RestDatasource>() {
            @Override
            public Predicate toPredicate(Root<RestDatasource> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                Class<?> clazz = query.getResultType();
                if (clazz.equals(RestDatasource.class)) {
                    root.fetch("requests", JoinType.LEFT);
                }
                return null;
            }
        };
    }
    
    public static Specification<RestDatasource> byId(final Long id) {
        return new Specification<RestDatasource>() {
            @Override
            public Predicate toPredicate(Root<RestDatasource> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.equal(root.<Long>get("id"), id);
            }
        };
    } 
     
    public static Specification<RestDatasource> byIdFetch(Long id) {
        return Specifications.where(byId(id)).and(rest());
    } 
     
}
