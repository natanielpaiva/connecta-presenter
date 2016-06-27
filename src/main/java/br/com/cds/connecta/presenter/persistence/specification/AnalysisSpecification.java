package br.com.cds.connecta.presenter.persistence.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;

public class AnalysisSpecification {

    public static Specification<Analysis> byDomain(final String domain) {
        return new Specification<Analysis>() {
            @Override
            public Predicate toPredicate(Root<Analysis> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.equal(root.<String>get("domain"), domain);
            }
        };
    }

    public static Specification<Analysis> byId(final Long id) {
        return new Specification<Analysis>() {
            @Override
            public Predicate toPredicate(Root<Analysis> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.equal(root.<Long>get("id"), id);
            }
        };
    }

    public static Specification<Analysis> byName(final String name) {
        return new Specification<Analysis>() {
            @Override
            public Predicate toPredicate(Root<Analysis> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.like(builder.upper(root.<String>get("name")), "%" + name.toUpperCase() + "%");
            }
        };
    }

    public static Specification<Analysis> byIds(final List<Long> ids) {
        return new Specification<Analysis>() {
            @Override
            public Predicate toPredicate(Root<Analysis> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                return root.<Long>get("id").in(ids);
            }
        };
    }

    public static Specification<Analysis> fetchCompleteData() {
        return new Specification<Analysis>() {
            @Override
            public Predicate toPredicate(Root<Analysis> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                Class<?> clazz = query.getResultType();
                if (clazz.equals(Analysis.class)) {
                    root.fetch("analysisColumns", JoinType.LEFT);
                    root.fetch("analysisAttributes", JoinType.LEFT);
                    root.fetch("datasource", JoinType.LEFT);
                }
                return null;
            }
        };
    }

    public static Specification<Analysis> fetchSimpleData() {
        return new Specification<Analysis>() {
            @Override
            public Predicate toPredicate(Root<Analysis> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                Class<?> clazz = query.getResultType();
                if (clazz.equals(Analysis.class)) {
                    root.fetch("analysisColumns");
                    root.fetch("datasource", JoinType.LEFT);
                }
                return null;
            }
        };
    }

    public static Specification<Analysis> byIdAndDomain(Long id, String domain) {
        return Specifications.where(byId(id)).and(byDomain(domain));
    }

    public static Specification<Analysis> byIdsAndDomain(List<Long> ids, String domain) {
        return Specifications.where(byIds(ids)).and(byDomain(domain));
    }

    public static Specification<Analysis> byIdAndDomainWithCompleteFetch(Long id, String domain) {
        return Specifications.where(byId(id))
                .and(byDomain(domain)).and(fetchCompleteData());
    }

    public static Specification<Analysis> byNameAndDomainWithSimpleFetch(String name, String domain) {
        return Specifications.where(byName(name))
                .and(byDomain(domain)).and(fetchSimpleData());
    }
}
