package br.com.cds.connecta.presenter.persistence.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;

public class SingleSourceSpecification {
	public static Specification<SingleSource> byDomain(final String domain) {
		return new Specification<SingleSource>() {
			@Override
			public Predicate toPredicate(Root<SingleSource> root, 
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(builder.upper(root.<String> get("domain")), 
						domain.toUpperCase());
			}
		};
	}
	
	public static Specification<SingleSource> byId(final Long id) {
		return new Specification<SingleSource>() {
			@Override
			public Predicate toPredicate(Root<SingleSource> root, 
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.<Long> get("id"), id);
			}
		};
	}
	
	public static Specification<SingleSource> byIds(final List<Long> ids) {
		return new Specification<SingleSource>() {
			@Override
			public Predicate toPredicate(Root<SingleSource> root, 
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				return root.<Long> get("id").in(ids);
			}
		};
	}
	
	public static Specification<SingleSource> byName(final String name) {
		return new Specification<SingleSource>() {
			@Override
			public Predicate toPredicate(Root<SingleSource> root, 
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(builder.upper(root.<String> get("name")), 
						"%"+name.toUpperCase()+"%");
			}
		};
	}
	
	public static Specification<SingleSource> fetchAttribute() {
		return new Specification<SingleSource>() {
			@Override
			public Predicate toPredicate(Root<SingleSource> root, 
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				Class<?> clazz = query.getResultType();
				if(clazz.equals(SingleSource.class)){
					root.fetch("singleSourceAttributes");
				}
				return null;
			}
		};
	}
	
	public static Specification<SingleSource> byIdAndDomain(Long id, String domain) {
		return Specifications.where(byId(id)).and(byDomain(domain));
	}
	
    public static Specification<SingleSource> byIdAndDomainWithAttributeFetch(Long id, String domain) {
        return Specifications.where(byId(id)).and(byDomain(domain)).and(fetchAttribute());
    }
    
    public static Specification<SingleSource> byIdsAndDomain(List<Long> ids, String domain) {
        return Specifications.where(byIds(ids)).and(byDomain(domain));
    }
    
    public static Specification<SingleSource> byNameAndDomain(String name, String domain) {
        return Specifications.where(byName(name)).and(byDomain(domain));
    }
}
