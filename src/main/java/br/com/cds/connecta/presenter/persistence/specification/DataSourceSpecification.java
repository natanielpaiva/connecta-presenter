package br.com.cds.connecta.presenter.persistence.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;

public class DataSourceSpecification {
	public static Specification<Datasource> byDomain(final String domain) {
		return new Specification<Datasource>() {
			@Override
			public Predicate toPredicate(Root<Datasource> root, 
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(builder.upper(root.<String> get("domain")), domain.toUpperCase());
			}
		};
	}
	
	public static Specification<Datasource> byId(final Long id) {
		return new Specification<Datasource>() {
			@Override
			public Predicate toPredicate(Root<Datasource> root, 
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.<Long> get("id"), id);
			}
		};
	}
	
	public static Specification<Datasource> byIds(final List<Long> ids) {
		return new Specification<Datasource>() {
			@Override
			public Predicate toPredicate(Root<Datasource> root, 
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				return root.<Long> get("id").in(ids);
			}
		};
	}
	
    public static Specification<Datasource> byIdAndDomain(Long id, String domain) {
        return Specifications.where(byId(id)).and(byDomain(domain));
    }
    
    public static Specification<Datasource> byIdsAndDomain(List<Long> ids, String domain) {
        return Specifications.where(byIds(ids)).and(byDomain(domain));
    }
}
