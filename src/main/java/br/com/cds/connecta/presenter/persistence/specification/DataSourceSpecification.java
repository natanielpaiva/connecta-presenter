package br.com.cds.connecta.presenter.persistence.specification;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import br.com.cds.connecta.presenter.domain.DatasourceTypeEnum;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import br.com.cds.connecta.presenter.filter.DatasourceFilter;

public class DataSourceSpecification {

    public static Specification<Datasource> byDomain(final String domain) {
        return new Specification<Datasource>() {
            @Override
            public Predicate toPredicate(Root<Datasource> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.equal(builder.upper(root.<String>get("domain")), domain.toUpperCase());
            }
        };
    }

    public static Specification<Datasource> byId(final Long id) {
        return new Specification<Datasource>() {
            @Override
            public Predicate toPredicate(Root<Datasource> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.equal(root.<Long>get("id"), id);
            }
        };
    }

    public static Specification<Datasource> byIds(final List<Long> ids) {
        return new Specification<Datasource>() {
            @Override
            public Predicate toPredicate(Root<Datasource> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return root.<Long>get("id").in(ids);
            }
        };
    }
    
	public static Specification<Datasource> byColumnAndValue(String column, Object value) {
		return new Specification<Datasource>() {
			@Override
			public Predicate toPredicate(Root<Datasource> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if(DatasourceTypeEnum.class.equals(root.get(column).getJavaType())){
					return builder.equal(root.get(column), DatasourceTypeEnum.valueOf(value.toString()));
				}
				return builder.like(builder.upper(root.get(column)), "%" + value.toString().toUpperCase() + "%");
			}
		};
	}

    public static Specification<Datasource> byIdAndDomain(Long id, String domain) {
        return Specifications.where(byId(id)).and(byDomain(domain));
    }

    public static Specification<Datasource> byIdsAndDomain(List<Long> ids, String domain) {
        return Specifications.where(byIds(ids)).and(byDomain(domain));
    }
    
	public static Specification<Datasource> byFilter(DatasourceFilter datasourceFilter) {
		Specifications<Datasource> spec = Specifications.where(byDomain(datasourceFilter.getDomain()));

		if (datasourceFilter.getFilter() != null) {
			for (Map.Entry<String, Object> entry : datasourceFilter.getFilter().entrySet()) {
				spec = spec.and(byColumnAndValue(entry.getKey(), entry.getValue()));
			}
		}

		return spec;
	}
}
