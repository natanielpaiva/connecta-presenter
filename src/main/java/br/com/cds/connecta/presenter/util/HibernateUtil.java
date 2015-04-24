package br.com.cds.connecta.presenter.util;

import javax.persistence.Query;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class HibernateUtil {

    public static String toSql(Query query) {
        return query.unwrap(org.hibernate.Query.class).getQueryString();
    }
}
