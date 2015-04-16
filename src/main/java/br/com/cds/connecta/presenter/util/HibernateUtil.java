package br.com.cds.connecta.presenter.util;

import javax.persistence.TypedQuery;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class HibernateUtil {

    public static String toSql(TypedQuery typedQuery) {
        return typedQuery.unwrap(org.hibernate.Query.class).getQueryString();
    }
}
