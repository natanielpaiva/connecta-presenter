package br.com.cds.connecta.presenter.persistence.specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import br.com.cds.connecta.framework.connector.endeca.mdex.eql_parser.types.Where;
import br.com.cds.connecta.presenter.domain.ViewerTypeEnum;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import br.com.cds.connecta.presenter.filter.ViewerFilter;

public class ViewerSpecification {
	public static Specification<Viewer> byDomain(final String domain) {
		return new Specification<Viewer>() {
			@Override
			public Predicate toPredicate(Root<Viewer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(builder.upper(root.<String>get("domain")), domain.toUpperCase());
			}
		};
	}

	public static Specification<Viewer> byId(final Long id) {
		return new Specification<Viewer>() {
			@Override
			public Predicate toPredicate(Root<Viewer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.<Long>get("id"), id);
			}
		};
	}

	public static Specification<Viewer> byIds(final List<Long> ids) {
		return new Specification<Viewer>() {
			@Override
			public Predicate toPredicate(Root<Viewer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return root.<Long>get("id").in(ids);
			}
		};
	}

	public static Specification<Viewer> byColumnAndValue(String column, Object value) {
		return new Specification<Viewer>() {
			@Override
			public Predicate toPredicate(Root<Viewer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if(ViewerTypeEnum.class.equals(root.get(column).getJavaType())){
					return builder.equal(root.get(column), ViewerTypeEnum.valueOf(value.toString()));
				}
				return builder.like(builder.upper(root.get(column)), "%" + value.toString().toUpperCase() + "%");
			}
		};
	}

	public static Specification<Viewer> byIdAndDomain(Long id, String domain) {
		return Specifications.where(byId(id)).and(byDomain(domain));
	}

	public static Specification<Viewer> byIdsAndDomain(List<Long> ids, String domain) {
		return Specifications.where(byIds(ids)).and(byDomain(domain));
	}

	public static Specification<Viewer> byFilter(ViewerFilter viewerFilter) {
		Specifications<Viewer> spec = Specifications.where(byDomain(viewerFilter.getDomain()));

		if (viewerFilter.getFilter() != null) {
			for (Map.Entry<String, Object> entry : viewerFilter.getFilter().entrySet()) {
				spec = spec.and(byColumnAndValue(entry.getKey(), entry.getValue()));
			}
		}

		return spec;
	}
}
